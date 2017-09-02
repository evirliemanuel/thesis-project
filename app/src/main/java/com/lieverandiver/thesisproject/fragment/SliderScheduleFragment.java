package com.lieverandiver.thesisproject.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lieverandiver.thesisproject.R;
import com.lieverandiver.thesisproject.adapter.ScheduleAdapter;
import com.lieverandiver.thesisproject.helper.ScheduleHelper;
import com.lieverandiver.thesisproject.helper.TeacherHelper;
import com.remswork.project.alice.exception.ScheduleException;
import com.remswork.project.alice.model.Schedule;
import com.remswork.project.alice.service.impl.ScheduleServiceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SliderScheduleFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    List<Schedule> scheduleList = new ArrayList<>();
    ScheduleAdapter scheduleAdapter;
    private RecyclerView scheduleRecyclerView;
    private View customView;
    private Handler handler;
    private RelativeLayout progressBar;
    private RadioButton radioButtonToday;
    private RadioButton radioButtonTomorrow;
    private RadioButton radioButtonAll;
    private RadioGroup radioGroup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        customView = inflater.inflate(R.layout.home_fragment_slidebar_schedule, container, false);
        progressBar = (RelativeLayout) customView.findViewById(R.id.progressbar_schedule);
        scheduleRecyclerView = (RecyclerView)
                customView.findViewById(R.id.shedule_recyclerview);
        radioButtonToday = (RadioButton) customView.findViewById(R.id.schedule_today_rb);
        radioButtonTomorrow = (RadioButton) customView.findViewById(R.id.schedule_tomorrow_rb);
        radioButtonAll = (RadioButton) customView.findViewById(R.id.schedule_all_rb);
        radioGroup = (RadioGroup) customView.findViewById(R.id.radio_group_schedule);
        radioGroup.setOnCheckedChangeListener(this);
        handler = new Handler(getActivity().getMainLooper());
        progressBar.setVisibility(View.VISIBLE);
        radioButtonAll.setSelected(true);
        init();
        return customView;
    }

    public void init() {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    final TeacherHelper teacherHelper = new TeacherHelper(getContext());
                    if (teacherHelper.loadUser().get() != null) {
                        for (Schedule schedule : new ScheduleServiceImpl()
                                .getScheduleListByTeacherId(teacherHelper.loadUser().get().getId()))
                            scheduleList.add(schedule);
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            scheduleRecyclerView = (RecyclerView)
                                    customView.findViewById(R.id.shedule_recyclerview);
                            scheduleAdapter = new ScheduleAdapter(
                                    getContext(), scheduleList);
                            scheduleRecyclerView.setAdapter(scheduleAdapter);
                            LinearLayoutManager layoutManager =
                                    new LinearLayoutManager(getContext());
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            scheduleRecyclerView.setLayoutManager(layoutManager);
                            scheduleRecyclerView.setItemAnimator(new DefaultItemAnimator());
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    });
                } catch (ScheduleException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public synchronized void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.schedule_today_rb:
                progressBar.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final List<Schedule> newSchedules = new ArrayList<>();
                            final TeacherHelper teacherHelper = new TeacherHelper(getContext());
                            if (teacherHelper.loadUser().get() != null) {
                                try {
                                    for (Schedule schedule : new ScheduleServiceImpl()
                                            .getScheduleListByTeacherId(teacherHelper.loadUser().get().getId()))
                                        newSchedules.add(schedule);
                                } catch (ScheduleException e) {
                                    e.printStackTrace();
                                }
                            }

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    int size = scheduleList.size();
                                    for (int i = 0; i < size; i++) {
                                        Log.i("myTAG", "List size :" + scheduleList.size());
                                        if (scheduleList.size() > 0) {
                                            scheduleList.remove(0);
                                            scheduleAdapter.notifyItemRemoved(0);
                                        }
                                    }

                                    ScheduleHelper scheduleHelper = new ScheduleHelper();
                                    int day = 2;
                                    for (Schedule schedule : newSchedules) {
                                        if (scheduleHelper.dayInNumber(schedule.getDay()) == day) {
                                            scheduleList.add(schedule);
                                            scheduleAdapter.notifyItemRangeInserted(0, scheduleList.size());
                                        }
                                    }
                                    progressBar.setVisibility(View.INVISIBLE);
                                }
                            });

                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.schedule_tomorrow_rb :
                progressBar.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final List<Schedule> newSchedules = new ArrayList<>();
                            final TeacherHelper teacherHelper = new TeacherHelper(getContext());
                            if (teacherHelper.loadUser().get() != null) {
                                try {
                                    for (Schedule schedule : new ScheduleServiceImpl()
                                            .getScheduleListByTeacherId(teacherHelper.loadUser().get().getId()))
                                        newSchedules.add(schedule);
                                } catch (ScheduleException e) {
                                    e.printStackTrace();
                                }
                            }

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    int size = scheduleList.size();
                                    for (int i = 0; i < size; i++) {
                                        Log.i("myTAG", "List size :" + scheduleList.size());
                                        if (scheduleList.size() > 0) {
                                            scheduleList.remove(0);
                                            scheduleAdapter.notifyItemRemoved(0);
                                        }
                                    }

                                    ScheduleHelper scheduleHelper = new ScheduleHelper();
                                    int day = 4;
                                    for (Schedule schedule : newSchedules) {
                                        if (scheduleHelper.dayInNumber(schedule.getDay()) == day) {
                                            scheduleList.add(schedule);
                                            scheduleAdapter.notifyItemRangeInserted(0, scheduleList.size());
                                        }
                                    }
                                    progressBar.setVisibility(View.INVISIBLE);
                                }
                            });

                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.schedule_all_rb :
                progressBar.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final List<Schedule> newSchedules = new ArrayList<>();
                            final TeacherHelper teacherHelper = new TeacherHelper(getContext());
                            if (teacherHelper.loadUser().get() != null) {
                                try {
                                    for (Schedule schedule : new ScheduleServiceImpl()
                                            .getScheduleListByTeacherId(teacherHelper.loadUser().get().getId()))
                                        newSchedules.add(schedule);
                                } catch (ScheduleException e) {
                                    e.printStackTrace();
                                }
                            }
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    int size = scheduleList.size();
                                    for (int i = 0; i < size; i++) {
                                        Log.i("myTAG", "List size :" + scheduleList.size());
                                        if (scheduleList.size() > 0) {
                                            scheduleList.remove(0);
                                            scheduleAdapter.notifyItemRemoved(0);
                                        }
                                    }

                                    ScheduleHelper scheduleHelper = new ScheduleHelper();
                                    int day = 2;
                                    for (Schedule schedule : newSchedules) {
                                            scheduleList.add(schedule);
                                            scheduleAdapter.notifyItemRangeInserted(0, scheduleList.size());
                                    }
                                    progressBar.setVisibility(View.INVISIBLE);
                                }
                            });

                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
        }
    }
}

package com.lieverandiver.thesisproject.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.lieverandiver.thesisproject.R;
import com.lieverandiver.thesisproject.adapter.ScheduleAdapter;
import com.lieverandiver.thesisproject.helper.TeacherHelper;
import com.remswork.project.alice.exception.ScheduleException;
import com.remswork.project.alice.model.Schedule;
import com.remswork.project.alice.service.impl.ScheduleServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class SliderScheduleFragment extends Fragment {

    private RecyclerView scheduleRecyclerView;
    private View customView;
    private Handler handler;
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        customView = inflater.inflate(R.layout.home_fragment_slidebar_schedule, container, false);
        progressBar = (ProgressBar) customView.findViewById(R.id.progressbar_schedule);
        scheduleRecyclerView = (RecyclerView)
                customView.findViewById(R.id.shedule_recyclerview);
        handler = new Handler(getActivity().getMainLooper());
        progressBar.setVisibility(View.VISIBLE);
        init();
        return customView;
    }

    public void init() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    final List<Schedule> scheduleList = new ArrayList<>();
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
                            ScheduleAdapter scheduleAdapter = new ScheduleAdapter(
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
}

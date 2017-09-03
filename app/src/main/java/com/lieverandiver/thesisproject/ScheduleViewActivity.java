package com.lieverandiver.thesisproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ToggleButton;

import com.remswork.project.alice.exception.ClassException;
import com.remswork.project.alice.model.Schedule;
import com.remswork.project.alice.service.impl.ClassServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ScheduleViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnBack;
    private ToggleButton btnSearch;
    private Button btnSearchOk;
    private Button btnSearchCancel;
    private EditText editTextSearch;
    private FrameLayout frameLayoutSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_schedule_view);
        recyclerView = (RecyclerView) findViewById(R.id.teacher_schedule_recyclerview);
        init(getIntent().getExtras().getLong("classId"));

        btnSearch = (ToggleButton)findViewById(R.id._btn_search_schedule);
        btnSearchOk = (Button)findViewById(R.id._btn_search_ok_schedule);
        btnBack = (Button)findViewById(R.id.btn_back_schedule);
        editTextSearch =(EditText)findViewById(R.id.etxt_search_schedule);
        frameLayoutSearch = (FrameLayout)findViewById(R.id.frame_search_schedule);

        frameLayoutSearch.setVisibility(View.GONE);



        btnSearch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    frameLayoutSearch.setVisibility(View.VISIBLE);
                } else {
                    frameLayoutSearch.setVisibility(View.GONE);
                }
            }
        });






    }

    public void init(long classId) {
        try {
            ClassServiceImpl classService =  new ClassServiceImpl();
            List<Schedule> scheduleList = new ArrayList<>();
            Set<Schedule> scheduleSet = classService.getScheduleList(classId);

            for(Schedule schedule : scheduleSet)
                scheduleList.add(schedule);

            com.lieverandiver.thesisproject.adapter.ScheduleAdapter scheduleAdapter =
                    new com.lieverandiver.thesisproject.adapter.ScheduleAdapter(this, scheduleList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setAdapter(scheduleAdapter);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        } catch (ClassException e) {
            e.printStackTrace();
        }
    }
}

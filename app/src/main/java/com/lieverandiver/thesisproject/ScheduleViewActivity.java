package com.lieverandiver.thesisproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.remswork.project.alice.exception.ClassException;
import com.remswork.project.alice.model.Schedule;
import com.remswork.project.alice.service.impl.ClassServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ScheduleViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_schedule_view);
        recyclerView = (RecyclerView) findViewById(R.id.teacher_schedule_recyclerview);
        init(getIntent().getExtras().getLong("classId"));
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

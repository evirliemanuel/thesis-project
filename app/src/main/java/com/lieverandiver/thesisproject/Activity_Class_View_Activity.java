package com.lieverandiver.thesisproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.lieverandiver.thesisproject.adapter.ActivityResultAdapter;
import com.remswork.project.alice.model.Activity;
import com.remswork.project.alice.model.ActivityResult;
import com.remswork.project.alice.model.Student;
import com.remswork.project.alice.service.ActivityService;
import com.remswork.project.alice.service.impl.ActivityServiceImpl;
import com.remswork.project.alice.service.impl.ClassServiceImpl;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class Activity_Class_View_Activity extends AppCompatActivity {
    final ActivityService activityService = new ActivityServiceImpl();

    private TextView textViewDate;
    private TextView textViewName;
    private TextView textViewTotal;
    private RecyclerView recyclerViewView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_class_view_activity);

            Activity activity = activityService.getActivityById(getIntent().getExtras().getLong("activityId"));
            List<ActivityResult> resultList = new ArrayList<>();

            textViewDate = (TextView) findViewById(R.id.activity_date);
            textViewName = (TextView) findViewById(R.id.activity_name);
            textViewTotal = (TextView) findViewById(R.id.totascore);
            recyclerViewView = (RecyclerView) findViewById(R.id.grades_view);

            textViewDate.setText(activity.getDate());
            textViewName.setText(activity.getTitle());
            textViewTotal.setText(activity.getItemTotal() + "");

            for(Student s : new ClassServiceImpl().getStudentList(getIntent().getExtras().getLong("classId"))){
                resultList.add(activityService.getActivityResultByActivityAndStudentId(activity.getId(), s.getId()));
            }

            ActivityResultAdapter simpleActivityAdapter = new ActivityResultAdapter(this, resultList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            recyclerViewView.setAdapter(simpleActivityAdapter);
            recyclerViewView.setLayoutManager(layoutManager);
            recyclerViewView.setItemAnimator(new DefaultItemAnimator());
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}

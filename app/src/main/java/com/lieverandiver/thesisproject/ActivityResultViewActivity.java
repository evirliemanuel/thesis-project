package com.lieverandiver.thesisproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.lieverandiver.thesisproject.adapter.SimpleActivityAdapter;
import com.remswork.project.alice.exception.ClassException;
import com.remswork.project.alice.exception.GradingFactorException;
import com.remswork.project.alice.model.Activity;
import com.remswork.project.alice.model.ActivityResult;
import com.remswork.project.alice.model.Student;
import com.remswork.project.alice.service.ActivityService;
import com.remswork.project.alice.service.ClassService;
import com.remswork.project.alice.service.impl.ActivityServiceImpl;
import com.remswork.project.alice.service.impl.ClassServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class ActivityResultViewActivity extends AppCompatActivity {

    private static final String TAG = ActivityResultViewActivity.class.getSimpleName();

    private final ActivityService activityService = new ActivityServiceImpl();
    private final ClassService classService = new ClassServiceImpl();
    private final List<ActivityResult> resultList = new ArrayList<>();
    private Activity activity;

    private TextView textViewDate;
    private TextView textViewName;
    private TextView textViewTotal;
    private RecyclerView recyclerViewView;

    private long classId;
    private long termId;
    private long activityId;

    private class ActivityViewThread extends Thread {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String date = activity.getDate();
                        String title = activity.getTitle();
                        String itemTotal = String.valueOf(activity.getItemTotal());

                        textViewDate.setText(date);
                        textViewName.setText(title);
                        textViewTotal.setText(itemTotal);

                        for (Student s : classService.getStudentList(classId)) {
                            ActivityResult result = activityService.getActivityResultByActivityAndStudentId(activity.getId(), s.getId());
                            if(result != null)
                                resultList.add(result);
                        }

                        SimpleActivityAdapter simpleActivityAdapter = new SimpleActivityAdapter(ActivityResultViewActivity.this, resultList);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(ActivityResultViewActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                        recyclerViewView.setAdapter(simpleActivityAdapter);
                        recyclerViewView.setLayoutManager(layoutManager);
                        recyclerViewView.setItemAnimator(new DefaultItemAnimator());
                    } catch (ClassException| GradingFactorException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void init() {
        textViewDate = (TextView) findViewById(R.id.activity_date);
        textViewName = (TextView) findViewById(R.id.activity_name);
        textViewTotal = (TextView) findViewById(R.id.totascore);
        recyclerViewView = (RecyclerView) findViewById(R.id.grades_view);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            Log.i(TAG, "onCreate");
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_class_view_activity);

            classId = getIntent().getExtras().getLong("classId");
            activityId = getIntent().getExtras().getLong("activityId");
            termId = getIntent().getExtras().getLong("termId");
            activity = activityService.getActivityById(activityId);

            init();
            new ActivityViewThread().start();
        }catch (GradingFactorException e) {
            e.printStackTrace();
        }
    }
}

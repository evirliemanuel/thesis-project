package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lieverandiver.thesisproject.adapter.ActivityAdapter;
import com.remswork.project.alice.exception.GradingFactorException;
import com.remswork.project.alice.model.Activity;
import com.remswork.project.alice.service.ActivityService;
import com.remswork.project.alice.service.impl.ActivityServiceImpl;

import java.util.List;

import static com.lieverandiver.thesisproject.R.id.btn_backaddactivity;
import static com.lieverandiver.thesisproject.R.id.fragment_slidebar_cardview_schedule;
import static com.lieverandiver.thesisproject.R.id.recyclerview_view1;
import static com.lieverandiver.thesisproject.R.id.relative_clicked1;

public class ActivityAddActivity extends AppCompatActivity implements ActivityAdapter.OnClickListener,
        View.OnClickListener {

    private static final String TAG = ActivityAddActivity.class.getSimpleName();

    final ActivityService activityService = new ActivityServiceImpl();
    private ImageView imageView;
    private Button btnBackButton;
    private RecyclerView recyclerView;
    private LinearLayout linearLayoutActivity;
    private long classId;
    private long termId;

    private class ActivityAddThread extends Thread {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        List<Activity> activityList = activityService.getActivityListByClassId(classId);
                        ActivityAdapter activityAdapter = new ActivityAdapter(ActivityAddActivity.this, activityList);

                        LinearLayoutManager layoutManager = new LinearLayoutManager(ActivityAddActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                        recyclerView.setAdapter(activityAdapter);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                    }catch (GradingFactorException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void init() {
        linearLayoutActivity = (LinearLayout) findViewById(relative_clicked1);
        recyclerView = (RecyclerView) findViewById(recyclerview_view1);
        btnBackButton = (Button) findViewById(btn_backaddactivity);

        linearLayoutActivity.setOnClickListener(this);
        btnBackButton.setOnClickListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_add_activity);
        try {
            classId = getIntent().getExtras().getLong("classId");
            termId = getIntent().getExtras().getLong("termId");

            init();
            new ActivityAddThread().start();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(Activity activity, long activityId) {
        Intent intent = getIntent();
        intent.putExtra("activityId", activityId);
        intent.setClass(this, ActivityResultViewActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case relative_clicked1 :
                Intent intent = getIntent().setClass(this, Activity_Class_InputActivity.class);
                startActivity(intent);
                break;
            case btn_backaddactivity :
                finish();
                break;
        }
    }
}

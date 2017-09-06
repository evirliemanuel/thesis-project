package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lieverandiver.thesisproject.adapter.ActivityAdapter;
import com.remswork.project.alice.model.Activity;
import com.remswork.project.alice.service.ActivityService;
import com.remswork.project.alice.service.impl.ActivityServiceImpl;

/**
 * Created by Verlie on 9/1/2017.
 */

@Deprecated
public class Activity_Class_Add_Activity extends AppCompatActivity implements ActivityAdapter.OnClickListener {

    final ActivityService activityService = new ActivityServiceImpl();
    private ImageView imageView;
    private Button btnBackButton;
    private RecyclerView recyclerView;
    private LinearLayout linearLayoutActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_add_activity);
        try {

            long classId = getIntent().getExtras().getLong("classId");

            Log.i("Class id : ", classId + "");

            linearLayoutActivity = (LinearLayout) findViewById(R.id.relative_clicked1);
            recyclerView = (RecyclerView) findViewById(R.id.recyclerview_view1);
            btnBackButton = (Button) findViewById(R.id.btn_backaddactivity);

            ActivityAdapter activityAdapter = new ActivityAdapter(this,
                    activityService.getActivityListByClassId(getIntent().getExtras().getLong("classId")));
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setAdapter(activityAdapter);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            linearLayoutActivity.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = getIntent().setClass(Activity_Class_Add_Activity.this,
                            Activity_Class_InputActivity.class);
                    startActivity(intent);

                }
            });

            btnBackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    finish();
                }
            });
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
}

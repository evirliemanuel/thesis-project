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
import com.lieverandiver.thesisproject.adapter.ProjectAdapter;
import com.remswork.project.alice.exception.GradingFactorException;
import com.remswork.project.alice.model.Activity;
import com.remswork.project.alice.model.Project;
import com.remswork.project.alice.service.ProjectService;
import com.remswork.project.alice.service.impl.ProjectServiceImpl;

import java.util.List;
import static com.lieverandiver.thesisproject.R.id.btn_backaddactivity;
import static com.lieverandiver.thesisproject.R.id.newid_projectl_clickednew;
import static com.lieverandiver.thesisproject.R.id.newid_projectr_clickednew;
import static com.lieverandiver.thesisproject.R.id.relative_clicked1;
public class ProjectAddActivity extends AppCompatActivity implements ActivityAdapter.OnClickListener,
        View.OnClickListener {

    private static final String TAG = ProjectAddActivity.class.getSimpleName();

    final ProjectService projectService = new ProjectServiceImpl();
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
                        List<Project> projectList = projectService.getProjectListByClassId(classId);
                        ProjectAdapter projectAdapter = new ProjectAdapter(ProjectAddActivity.this, projectList);

                        LinearLayoutManager layoutManager = new LinearLayoutManager(ProjectAddActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                        recyclerView.setAdapter(projectAdapter);
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
        linearLayoutActivity = (LinearLayout) findViewById(newid_projectl_clickednew);
        recyclerView = (RecyclerView) findViewById(newid_projectr_clickednew);
        btnBackButton = (Button) findViewById(btn_backaddactivity);

        linearLayoutActivity.setOnClickListener(this);
        btnBackButton.setOnClickListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_add_project);
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
            case newid_projectl_clickednew :
                Intent intent = getIntent().setClass(this, ActivityInputActivity.class);
                startActivity(intent);
                break;
            case btn_backaddactivity :
                finish();
                break;
        }
    }
}

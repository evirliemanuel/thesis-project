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

import com.lieverandiver.thesisproject.adapter.AssignmentAdapter;
import com.remswork.project.alice.exception.GradingFactorException;
import com.remswork.project.alice.model.Assignment;
import com.remswork.project.alice.service.AssignmentService;
import com.remswork.project.alice.service.impl.AssignmentServiceImpl;
import java.util.List;
import static com.lieverandiver.thesisproject.R.id.assignmentl_clicked;
import static com.lieverandiver.thesisproject.R.id.btn_backassignment;

public class AssignmentAddActivity extends AppCompatActivity implements AssignmentAdapter.OnClickListener,
        View.OnClickListener {

    private static final String TAG = AssignmentAddActivity.class.getSimpleName();

    final AssignmentService aassignmentService = new AssignmentServiceImpl();
    private ImageView imageView;
    private Button btnBackButton;
    private RecyclerView recyclerView;
    private LinearLayout linearLayoutActivity;
    private long classId;
    private long termId;

    private class AssignmentAddThread extends Thread {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        List<Assignment> assignmentList = aassignmentService.getAssignmentListByClassId(classId);
                        AssignmentAdapter assignmentAdapter = new AssignmentAdapter(AssignmentAddActivity.this, assignmentList);

                        LinearLayoutManager layoutManager = new LinearLayoutManager(AssignmentAddActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                        recyclerView.setAdapter(assignmentAdapter);
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
        linearLayoutActivity = (LinearLayout) findViewById(R.id.assignmentl_clicked);
        recyclerView = (RecyclerView) findViewById(R.id.assignmentr_clicked);
        btnBackButton = (Button) findViewById(R.id.btn_backassignment);

        linearLayoutActivity.setOnClickListener(this);
        btnBackButton.setOnClickListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_add_assignment);
        try {
            classId = getIntent().getExtras().getLong("classId");
            termId = getIntent().getExtras().getLong("termId");

            init();
            new AssignmentAddThread().start();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(Assignment assignment, long assignmentId) {
        Intent intent = getIntent();
        intent.putExtra("assignmentId", assignmentId);
        intent.setClass(this, ActivityResultViewActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case assignmentl_clicked :
                Intent intent = getIntent().setClass(this, AssignmentInputActivity.class);
                startActivity(intent);
                break;
            case btn_backassignment :
                finish();
                break;
        }
    }
}
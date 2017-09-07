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

import com.lieverandiver.thesisproject.adapter.ExamAdapter;
import com.remswork.project.alice.exception.GradingFactorException;
import com.remswork.project.alice.model.Exam;
import com.remswork.project.alice.service.ExamService;
import com.remswork.project.alice.service.impl.ExamServiceImpl;

import java.util.List;

import static com.lieverandiver.thesisproject.R.id.btn_backaddexam;

import static com.lieverandiver.thesisproject.R.id.newid_examl_clickednew;

import static com.lieverandiver.thesisproject.R.id.newid_examr_clickednew;

public class ExamAddActivity extends AppCompatActivity implements ExamAdapter.OnClickListener,
        View.OnClickListener {

    private static final String TAG = ExamAddActivity.class.getSimpleName();

    final ExamService examService = new ExamServiceImpl();
    private ImageView imageView;
    private Button btnBackButton;
    private RecyclerView recyclerView;
    private LinearLayout linearLayoutActivity;
    private long classId;
    private long termId;

    private class ExamAddThread extends Thread {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        List<Exam> examList = examService.getExamListByClassId(classId);
                        ExamAdapter examAdapter = new ExamAdapter(ExamAddActivity.this, examList);

                        LinearLayoutManager layoutManager = new LinearLayoutManager(ExamAddActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                        recyclerView.setAdapter(examAdapter);
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
        linearLayoutActivity = (LinearLayout) findViewById(newid_examl_clickednew);
        recyclerView = (RecyclerView) findViewById(newid_examr_clickednew);
        btnBackButton = (Button) findViewById(btn_backaddexam);

        linearLayoutActivity.setOnClickListener(this);
        btnBackButton.setOnClickListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_add_exam);
        try {
            classId = getIntent().getExtras().getLong("classId");
            termId = getIntent().getExtras().getLong("termId");

            init();
            new ExamAddThread().start();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(Exam exam, long examId) {
        Intent intent = getIntent();
        intent.putExtra("examId", examId);
        intent.setClass(this, ActivityResultViewActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case newid_examl_clickednew :
                Intent intent = getIntent().setClass(this,ExamInputActivity.class);
                startActivity(intent);
                break;
            case newid_examr_clickednew :
                finish();
                break;
        }
    }
}

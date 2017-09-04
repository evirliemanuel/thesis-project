package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.remswork.project.alice.exception.ClassException;
import com.remswork.project.alice.model.Class;
import com.remswork.project.alice.service.impl.ClassServiceImpl;

public class ClassViewActivity extends AppCompatActivity {

    private TextView txtViewSubjectName;
    private TextView txtViewSectionName;
    private TextView txtViewDepName;
    private TextView txtViewSemName;
    private CardView viewStudent;
    private CardView viewSchedule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activty_view_class);

        viewSchedule = (CardView) findViewById(R.id.view_schedule);
        viewStudent = (CardView) findViewById(R.id.view_student);
        txtViewSubjectName = (TextView) findViewById(R.id.txtv_subjectname);
        txtViewSectionName = (TextView) findViewById(R.id.txtv_section);
        txtViewDepName = (TextView) findViewById(R.id.txtv_dept);
        txtViewSemName = (TextView) findViewById(R.id.txtv_sem);

        init(getIntent().getExtras().getLong("classId"));
    }

    public void init(final long classId) {
        final Handler handler = new Handler(getMainLooper());
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ClassServiceImpl classService = new  ClassServiceImpl();
                            Class _class = classService.getClassById(classId);
                            txtViewSubjectName
                                    .setText((_class.getSubject() != null ? _class.getSubject()
                                            .getName() : "None"));
                            txtViewSectionName
                                    .setText((_class.getSection() != null ? _class.getSection()
                                            .getName() : "None"));
                            txtViewDepName
                                    .setText((_class.getSection() != null ? _class.getSection()
                                            .getDepartment().getName() : "None"));
                            viewSchedule.setOnClickListener(new Button.OnClickListener(){
                                @Override
                                public synchronized void onClick(View v) {
                                    Intent intent = getIntent().setClass(ClassViewActivity.this,
                                            ScheduleViewActivity.class);
                                    startActivity(intent);
                                }
                            });

                            viewStudent.setOnClickListener(new Button.OnClickListener(){
                                @Override
                                public synchronized void onClick(View v) {
                                    Intent intent =
                                            getIntent().setClass(ClassViewActivity.this,
                                                    StudentViewActivity.class);
                                    startActivity(intent);
                                }
                            });
                        } catch (ClassException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

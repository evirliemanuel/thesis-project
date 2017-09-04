package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.remswork.project.alice.exception.ClassException;
import com.remswork.project.alice.model.Class;
import com.remswork.project.alice.service.ClassService;

import static com.lieverandiver.thesisproject.R.id.view_schedule;
import static com.lieverandiver.thesisproject.R.id.view_student;

public class ClassViewActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ClassViewActivity.class.getSimpleName();

    private ClassService classService;
    private TextView txtViewSubjectName;
    private TextView txtViewSectionName;
    private TextView txtViewDepName;
    private TextView txtFormat;
    private CardView viewStudent;
    private CardView viewSchedule;
    private long classId;

    public class ClassViewThread extends Thread {
        @Override
        public void run() {
            try {
                final Class _class = classService.getClassById(classId);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        txtViewSubjectName.setText((_class.getSubject() != null ?
                                _class.getSubject().getName() : "None"));
                        txtViewSectionName.setText((_class.getSection() != null ?
                                _class.getSection().getName() : "None"));
                        txtViewDepName.setText((_class.getSection() != null ?
                                _class.getSection().getDepartment().getName() : "None"));
                    }
                });

            } catch (ClassException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activty_view_class);

        init();
        classId = getIntent().getExtras().getLong("classId");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case view_schedule :
                Intent intent = getIntent().setClass(this, ScheduleViewActivity.class);
                startActivity(intent);
                break;
            case view_student :
                intent = getIntent().setClass(this, StudentViewActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void init() {
        viewSchedule = (CardView) findViewById(view_schedule);
        viewStudent = (CardView) findViewById(view_student);
        txtViewSubjectName = (TextView) findViewById(R.id.txtv_subjectname);
        txtViewSectionName = (TextView) findViewById(R.id.txtv_section);
        txtViewDepName = (TextView) findViewById(R.id.txtv_dept);
        txtFormat = (TextView) findViewById(R.id.txtv_sem);

        viewSchedule.setOnClickListener(this);
        viewStudent.setOnClickListener(this);
    }

}

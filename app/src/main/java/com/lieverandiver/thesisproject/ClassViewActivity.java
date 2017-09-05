package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.remswork.project.alice.exception.ClassException;
import com.remswork.project.alice.model.Class;
import com.remswork.project.alice.service.ClassService;

import static com.lieverandiver.thesisproject.R.id.view_schedule;
import static com.lieverandiver.thesisproject.R.id.view_student;
import static com.lieverandiver.thesisproject.R.id.viewactivityf;
import static com.lieverandiver.thesisproject.R.id.viewactivitym;
import static com.lieverandiver.thesisproject.R.id.viewassignmentf;
import static com.lieverandiver.thesisproject.R.id.viewassignmentm;
import static com.lieverandiver.thesisproject.R.id.viewattendancef;
import static com.lieverandiver.thesisproject.R.id.viewattendancem;
import static com.lieverandiver.thesisproject.R.id.viewexamf;
import static com.lieverandiver.thesisproject.R.id.viewexamm;
import static com.lieverandiver.thesisproject.R.id.viewprojectf;
import static com.lieverandiver.thesisproject.R.id.viewprojectm;
import static com.lieverandiver.thesisproject.R.id.viewquizf;
import static com.lieverandiver.thesisproject.R.id.viewquizm;

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

    private ToggleButton toggleButtonShowandHideM;
    private ToggleButton toggleButtonShowandHideF;
    private LinearLayout linearLayoutShowandHideM;
    private LinearLayout linearLayoutShowandHideF;

    private LinearLayout linearLayoutActivityM;
    private LinearLayout linearLayoutAssignmentM;
    private LinearLayout linearLayoutAttendanceM;
    private LinearLayout linearLayoutExamM;
    private LinearLayout linearLayoutProjectM;
    private LinearLayout linearLayoutQuizM;

    private LinearLayout linearLayoutActivityF;
    private LinearLayout linearLayoutAssignmentF;
    private LinearLayout linearLayoutAttendanceF;
    private LinearLayout linearLayoutExamF;
    private LinearLayout linearLayoutProjectF;
    private LinearLayout linearLayoutQuizF;

    private Button buttonBack;

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

            case viewactivitym :
                intent = getIntent().setClass(this, Activity_Class_Add_Activity.class);
                startActivity(intent);
                break;

            case viewassignmentm :
                intent = getIntent().setClass(this, Activity_Class_Add_Assignment.class);
                startActivity(intent);
                break;

            case viewattendancem :
                intent = getIntent().setClass(this, Activity_Class_Add_Attendance.class);
                startActivity(intent);
                break;

            case viewexamm :
                intent = getIntent().setClass(this, Activity_Class_Add_Exam.class);
                startActivity(intent);
                break;

            case viewprojectm :
                intent = getIntent().setClass(this, Activity_Class_Add_Project.class);
                startActivity(intent);
                break;

            case viewquizm :
                intent = getIntent().setClass(this, Activity_Class_Add_Quiz.class);
                startActivity(intent);
                break;

            case viewactivityf :
                intent = getIntent().setClass(this, Activity_Class_Add_Activity.class);
                startActivity(intent);
                break;

            case viewassignmentf :
                intent = getIntent().setClass(this, Activity_Class_Add_Assignment.class);
                startActivity(intent);
                break;

            case viewattendancef :
                intent = getIntent().setClass(this, Activity_Class_Add_Attendance.class);
                startActivity(intent);
                break;

            case viewexamf :
                intent = getIntent().setClass(this, Activity_Class_Add_Exam.class);
                startActivity(intent);
                break;

            case viewprojectf :
                intent = getIntent().setClass(this, Activity_Class_Add_Project.class);
                startActivity(intent);
                break;

            case viewquizf :
                intent = getIntent().setClass(this, Activity_Class_Add_Quiz.class);
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
        buttonBack.setOnClickListener(this);



        toggleButtonShowandHideM = (ToggleButton)findViewById(R.id.mtogglebutton);
        toggleButtonShowandHideF = (ToggleButton)findViewById(R.id.ftogglebutton);
        linearLayoutShowandHideM = (LinearLayout)findViewById(R.id.mlinear_showandhide);
        linearLayoutShowandHideF = (LinearLayout)findViewById(R.id.flinear_showandhide);

        linearLayoutActivityM =(LinearLayout)findViewById(R.id.viewactivitym);
        linearLayoutAssignmentM =(LinearLayout)findViewById(R.id.viewassignmentm);
        linearLayoutAttendanceM =(LinearLayout)findViewById(R.id.viewattendancem);
        linearLayoutExamM =(LinearLayout)findViewById(R.id.viewexamm);
        linearLayoutProjectM =(LinearLayout)findViewById(R.id.viewprojectm);
        linearLayoutQuizM =(LinearLayout)findViewById(R.id.viewquizm);

        linearLayoutActivityF =(LinearLayout)findViewById(viewactivityf);
        linearLayoutAssignmentF =(LinearLayout)findViewById(viewassignmentf);
        linearLayoutAttendanceF =(LinearLayout)findViewById(viewattendancef);
        linearLayoutExamF =(LinearLayout)findViewById(viewexamf);
        linearLayoutProjectF =(LinearLayout)findViewById(viewprojectf);
        linearLayoutQuizF =(LinearLayout)findViewById(viewquizf);

        linearLayoutActivityM.setOnClickListener(this);
        linearLayoutAssignmentM.setOnClickListener(this);
        linearLayoutAttendanceM.setOnClickListener(this);
        linearLayoutExamM.setOnClickListener(this);
        linearLayoutProjectM.setOnClickListener(this);
        linearLayoutQuizM.setOnClickListener(this);

        linearLayoutActivityF.setOnClickListener(this);
        linearLayoutAssignmentF.setOnClickListener(this);
        linearLayoutAttendanceF.setOnClickListener(this);
        linearLayoutExamF.setOnClickListener(this);
        linearLayoutProjectF.setOnClickListener(this);
        linearLayoutQuizF.setOnClickListener(this);

        linearLayoutActivityM.setVisibility(View.VISIBLE);
        linearLayoutAssignmentM.setVisibility(View.VISIBLE);
        linearLayoutAttendanceM.setVisibility(View.VISIBLE);
        linearLayoutExamM.setVisibility(View.VISIBLE);
        linearLayoutProjectM.setVisibility(View.VISIBLE);
        linearLayoutQuizM.setVisibility(View.VISIBLE);

        linearLayoutActivityF.setVisibility(View.VISIBLE);
        linearLayoutAssignmentF.setVisibility(View.VISIBLE);
        linearLayoutAttendanceF.setVisibility(View.VISIBLE);
        linearLayoutExamF.setVisibility(View.VISIBLE);
        linearLayoutProjectF.setVisibility(View.VISIBLE);
        linearLayoutQuizF.setVisibility(View.VISIBLE);

        linearLayoutShowandHideF.setVisibility(View.GONE);
        linearLayoutShowandHideM.setVisibility(View.GONE);

        toggleButtonShowandHideM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    linearLayoutShowandHideM.setVisibility(View.VISIBLE);
                } else {
                    linearLayoutShowandHideM.setVisibility(View.GONE);
                }
            }
        });

        toggleButtonShowandHideF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    linearLayoutShowandHideF.setVisibility(View.VISIBLE);
                } else {
                    linearLayoutShowandHideF.setVisibility(View.GONE);
                }
            }
        });


    }

}

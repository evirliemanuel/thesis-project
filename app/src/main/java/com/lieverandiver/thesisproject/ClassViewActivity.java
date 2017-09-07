package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.remswork.project.alice.exception.ClassException;
import com.remswork.project.alice.exception.GradingFactorException;
import com.remswork.project.alice.model.Class;
import com.remswork.project.alice.service.ActivityService;
import com.remswork.project.alice.service.AttendanceService;
import com.remswork.project.alice.service.ClassService;
import com.remswork.project.alice.service.impl.ActivityServiceImpl;
import com.remswork.project.alice.service.impl.AttendanceServiceImpl;
import com.remswork.project.alice.service.impl.ClassServiceImpl;

import static com.lieverandiver.thesisproject.R.id.ftogglebutton;
import static com.lieverandiver.thesisproject.R.id.mtogglebutton;
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

public class ClassViewActivity extends AppCompatActivity implements View.OnClickListener ,
        CompoundButton.OnCheckedChangeListener {

    private static final String TAG = ClassViewActivity.class.getSimpleName();

    private final ActivityService activityService = new ActivityServiceImpl();
    private final AttendanceService attendanceService = new AttendanceServiceImpl();
    private final ClassService classService = new ClassServiceImpl();
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

    private TextView textViewActivityM;
    private TextView textViewAssignmentM;
    private TextView textViewAttendanceM;
    private TextView textViewExamM;
    private TextView textViewProjectM;
    private TextView textViewQuizM;

    private TextView textViewActivityF;
    private TextView textViewAssignmentF;
    private TextView textViewAttendanceF;
    private TextView textViewExamF;
    private TextView textViewProjectF;
    private TextView textViewQuizF;

    public void init() {

        textViewActivityM = (TextView)findViewById(R.id.totalm1m);
        textViewAssignmentM = (TextView)findViewById(R.id.totalm2);
        textViewAttendanceM = (TextView)findViewById(R.id.totalm3);
        textViewExamM = (TextView)findViewById(R.id.totalm4);
        textViewProjectM = (TextView)findViewById(R.id.totalm5);
        textViewQuizM = (TextView)findViewById(R.id.totalm6);

        textViewActivityF = (TextView)findViewById(R.id.totalf1);
        textViewAssignmentF = (TextView)findViewById(R.id.totalf2);
        textViewAttendanceF = (TextView)findViewById(R.id.totalf3);
        textViewExamF = (TextView)findViewById(R.id.totalf4);
        textViewProjectF = (TextView)findViewById(R.id.totalf5);
        textViewQuizF = (TextView)findViewById(R.id.totalf6);

        viewSchedule = (CardView) findViewById(view_schedule);
        viewStudent = (CardView) findViewById(view_student);
        txtViewSubjectName = (TextView) findViewById(R.id.txtv_subjectname);
        txtViewSectionName = (TextView) findViewById(R.id.txtv_section);
        txtViewDepName = (TextView) findViewById(R.id.txtv_dept);
        txtFormat = (TextView) findViewById(R.id.txtv_sem);

        viewSchedule.setOnClickListener(this);
        viewStudent.setOnClickListener(this);

        toggleButtonShowandHideM = (ToggleButton)findViewById(mtogglebutton);
        toggleButtonShowandHideF = (ToggleButton)findViewById(ftogglebutton);
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

        toggleButtonShowandHideM.setOnCheckedChangeListener(this);
        toggleButtonShowandHideF.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch(buttonView.getId()) {
            case mtogglebutton :
            if(isChecked)
                linearLayoutShowandHideM.setVisibility(View.VISIBLE);
            else
              linearLayoutShowandHideM.setVisibility(View.GONE);
            break;
            case ftogglebutton :
                if(isChecked)
                    linearLayoutShowandHideF.setVisibility(View.VISIBLE);
                else
                    linearLayoutShowandHideF.setVisibility(View.GONE);
                break;
        }
    }

    public class ClassViewThread extends Thread {

        @Override
        public void run() {
            try {
                final int sizeAc = activityService.getActivityListByClassId(classId, 1L).size();
                final int sizeAtt = attendanceService.getAttendanceListByClassId(classId, 1L).size();
                final Class _class = classService.getClassById(classId);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                            String subjectName = (_class.getSubject() != null ? _class.getSubject().getName() : "None");
                            String sectionName = (_class.getSection() != null ? _class.getSection().getName() : "None");
                            String departmentName = (_class.getSection() != null ? _class.getSection().getDepartment().getName() : "None");
                            String totalActivitySize = String.valueOf(sizeAc);
                            String totalAttendanceSize = String.valueOf(sizeAtt);
                            txtViewSubjectName.setText(subjectName);
                            txtViewSectionName.setText(sectionName);
                            txtViewDepName.setText(departmentName);
                            textViewActivityM.setText(totalActivitySize);
                            textViewAttendanceM.setText(totalAttendanceSize);

                    }
                });
            } catch (GradingFactorException e) {

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
        try {
            classId = getIntent().getExtras().getLong("classId");
            init();
            new ClassViewThread().start();
        }catch (Exception e) {
            e.printStackTrace();
        }

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
                intent = getIntent().setClass(this, ActivityAddActivity.class);
                intent.putExtra("termId", 1);
                startActivity(intent);
                break;
            case viewassignmentm :
                intent = getIntent().setClass(this, Activity_Class_Add_Assignment.class);
                startActivity(intent);
                break;
            case viewattendancem :
                intent = getIntent().setClass(this, AttendanceAddActivity.class);
                intent.putExtra("termId", 1L);
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
}

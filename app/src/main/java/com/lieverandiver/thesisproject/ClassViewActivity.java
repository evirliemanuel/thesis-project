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
import com.remswork.project.alice.exception.GradingFactorException;
import com.remswork.project.alice.model.Class;
import com.remswork.project.alice.service.ActivityService;

import com.remswork.project.alice.service.AttendanceService;
import com.remswork.project.alice.service.ClassService;
import com.remswork.project.alice.service.ExamService;
import com.remswork.project.alice.service.ProjectService;
import com.remswork.project.alice.service.QuizService;
import com.remswork.project.alice.service.impl.ActivityServiceImpl;
import com.remswork.project.alice.service.impl.AttendanceServiceImpl;
import com.remswork.project.alice.service.AssignmentService;
import com.remswork.project.alice.service.impl.AssignmentServiceImpl;
import com.remswork.project.alice.service.impl.ClassServiceImpl;
import com.remswork.project.alice.service.impl.ExamServiceImpl;
import com.remswork.project.alice.service.impl.ProjectServiceImpl;
import com.remswork.project.alice.service.impl.QuizServiceImpl;

import static com.lieverandiver.thesisproject.R.id.class_backbutton;
import static com.lieverandiver.thesisproject.R.id.ftogglebutton;
import static com.lieverandiver.thesisproject.R.id.mtogglebutton;
import static com.lieverandiver.thesisproject.R.id.view_grade;
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
    private final AssignmentService assignmentService = new AssignmentServiceImpl();
    private final ExamService examService = new ExamServiceImpl();
    private final ProjectService projectService = new ProjectServiceImpl();
    private final QuizService quizService = new QuizServiceImpl();


    private final ClassService classService = new ClassServiceImpl();

    private TextView txtViewSubjectName;
    private TextView txtViewSectionName;
    private TextView txtViewDepName;
    private TextView txtFormat;
    private CardView viewStudent;
    private CardView viewSchedule;
    private CardView viewGrade;
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

    private Button btnBack;

    public void init() {

        textViewActivityM = (TextView)findViewById(R.id.totalm1);
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

        viewSchedule = (CardView) findViewById(R.id.view_schedule);
        viewStudent = (CardView) findViewById(R.id.view_student);
        viewGrade = (CardView) findViewById(R.id.view_grade);
        txtViewSubjectName = (TextView) findViewById(R.id.txtv_subjectname);
        txtViewSectionName = (TextView) findViewById(R.id.txtv_section);
        txtViewDepName = (TextView) findViewById(R.id.txtv_dept);
        txtFormat = (TextView) findViewById(R.id.txtv_sem);
        btnBack = (Button)findViewById(R.id.class_backbutton);

        viewSchedule.setOnClickListener(this);
        viewStudent.setOnClickListener(this);
        viewGrade.setOnClickListener(this);

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

        btnBack.setOnClickListener(this);

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
                final int sizeAss = assignmentService.getAssignmentListByClassId(classId, 1L).size();
                final int sizeExam = examService.getExamListByClassId(classId, 1L).size();
                final int sizePro = projectService.getProjectListByClassId(classId, 1L).size();
                final int sizeQuiz = quizService.getQuizListByClassId(classId, 1L).size();
                final int sizeAcF = activityService.getActivityListByClassId(classId, 2L).size();
                final int sizeAttF = attendanceService.getAttendanceListByClassId(classId, 2L).size();
                final int sizeAssF = assignmentService.getAssignmentListByClassId(classId, 2L).size();
                final int sizeExamF = examService.getExamListByClassId(classId, 2L).size();
                final int sizeProF = projectService.getProjectListByClassId(classId, 2L).size();
                final int sizeQuizF = quizService.getQuizListByClassId(classId, 2L).size();


                final Class _class = classService.getClassById(classId);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                            String subjectName = (_class.getSubject() != null ? _class.getSubject().getName() : "None");
                            String sectionName = (_class.getSection() != null ? _class.getSection().getName() : "None");
                            String departmentName = (_class.getSection() != null ? _class.getSection().getDepartment().getName() : "None");
                            String totalActivitySize = String.valueOf(sizeAc);
                            String totalAttendanceSize = String.valueOf(sizeAtt);
                            String totalAssignmentSize = String.valueOf(sizeAss);
                            String totalExamSize = String.valueOf(sizeExam);
                            String totalProjectSize = String.valueOf(sizePro);
                            String totalQuizSize = String.valueOf(sizeQuiz);

                        String totalActivitySizeF = String.valueOf(sizeAcF);
                        String totalAttendanceSizeF = String.valueOf(sizeAttF);
                        String totalAssignmentSizeF = String.valueOf(sizeAssF);
                        String totalExamSizeF = String.valueOf(sizeExamF);
                        String totalProjectSizeF = String.valueOf(sizeProF);
                        String totalQuizSizeF = String.valueOf(sizeQuizF);

                            txtViewSubjectName.setText(subjectName);
                            txtViewSectionName.setText(sectionName);
                            txtViewDepName.setText(departmentName);
                            textViewActivityM.setText(totalActivitySize);
                            textViewAttendanceM.setText(totalAttendanceSize);
                            textViewAssignmentM.setText(totalAssignmentSize);
                            textViewExamM.setText(totalExamSize);
                            textViewProjectM.setText(totalProjectSize);
                            textViewQuizM.setText(totalQuizSize);

                            textViewActivityF.setText(totalActivitySizeF);
                            textViewAttendanceF.setText(totalAttendanceSizeF);
                            textViewAssignmentF.setText(totalAssignmentSizeF);
                            textViewExamF.setText(totalExamSizeF);
                            textViewProjectF.setText(totalProjectSizeF);
                            textViewQuizF.setText(totalQuizSizeF);

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
            case view_grade :
                intent = getIntent().setClass(this, GradeViewActivity.class);
                startActivity(intent);
                break;
            case viewactivitym :
                intent = getIntent().setClass(this, ActivityAddActivity.class);
                intent.putExtra("termId", 1L);
                startActivity(intent);
                break;
            case viewassignmentm :
                intent = getIntent().setClass(this, AssignmentAddActivity.class);
                intent.putExtra("termId", 1L);
                startActivity(intent);
                break;
            case viewattendancem :
                intent = getIntent().setClass(this, AttendanceAddActivity.class);
                intent.putExtra("termId", 1L);
                startActivity(intent);
                break;
            case viewexamm :
                intent = getIntent().setClass(this, ExamAddActivity.class);
                intent.putExtra("termId", 1L);
                startActivity(intent);
                break;
            case viewprojectm :
                intent = getIntent().setClass(this, ProjectAddActivity.class);
                intent.putExtra("termId", 1L);
                startActivity(intent);
                break;

            case viewquizm :
                intent = getIntent().setClass(this, QuizAddActivity.class);
                intent.putExtra("termId", 1L);
                startActivity(intent);
                break;

            case viewactivityf :
                intent = getIntent().setClass(this, ActivityAddActivityF.class);
                intent.putExtra("termId", 2L);
                startActivity(intent);
                break;
            case viewassignmentf :
                intent = getIntent().setClass(this, AssignmentAddActivityF.class);
                intent.putExtra("termId", 2L);
                startActivity(intent);
                break;
            case viewattendancef :
                intent = getIntent().setClass(this, AttendanceAddActivity.class);
                intent.putExtra("termId",2L);
                startActivity(intent);
                break;
            case viewexamf :
                intent = getIntent().setClass(this, ExamAddActivityF.class);
                intent.putExtra("termId", 2L);
                startActivity(intent);
                break;
            case viewprojectf :
                intent = getIntent().setClass(this, ProjectAddActivityF.class);
                intent.putExtra("termId", 2L);
                startActivity(intent);
                break;

            case viewquizf :
                intent = getIntent().setClass(this, QuizAddActivityF.class);
                intent.putExtra("termId", 2L);
                startActivity(intent);
                break;

            case class_backbutton :
                intent = getIntent().setClass(this, MainActivity2.class);
                startActivity(intent);
                break;


        }
    }
}

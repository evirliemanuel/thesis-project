package com.lieverandiver.thesisproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.lieverandiver.thesisproject.helper.TeacherHelper;
import com.remswork.project.alice.exception.GradingFactorException;
import com.remswork.project.alice.model.Formula;
import com.remswork.project.alice.model.Subject;
import com.remswork.project.alice.model.Teacher;
import com.remswork.project.alice.service.FormulaService;
import com.remswork.project.alice.service.SubjectService;
import com.remswork.project.alice.service.impl.FormulaServiceImpl;
import com.remswork.project.alice.service.impl.SubjectServiceImpl;

import static com.lieverandiver.thesisproject.R.id.activity_seekbarf;
import static com.lieverandiver.thesisproject.R.id.activity_seekbarm;
import static com.lieverandiver.thesisproject.R.id.activity_switch_redf;
import static com.lieverandiver.thesisproject.R.id.activity_switch_redm;
import static com.lieverandiver.thesisproject.R.id.assignment_seekbarf;
import static com.lieverandiver.thesisproject.R.id.assignment_seekbarm;
import static com.lieverandiver.thesisproject.R.id.assignment_switch_redf;
import static com.lieverandiver.thesisproject.R.id.assignment_switch_redm;
import static com.lieverandiver.thesisproject.R.id.attendance_seekbarf;
import static com.lieverandiver.thesisproject.R.id.attendance_seekbarm;
import static com.lieverandiver.thesisproject.R.id.attendance_switch_redf;
import static com.lieverandiver.thesisproject.R.id.attendance_switch_redm;
import static com.lieverandiver.thesisproject.R.id.exam_seekbarf;
import static com.lieverandiver.thesisproject.R.id.exam_seekbarm;
import static com.lieverandiver.thesisproject.R.id.exam_switch_redf;
import static com.lieverandiver.thesisproject.R.id.exam_switch_redm;
import static com.lieverandiver.thesisproject.R.id.project_seekbarf;
import static com.lieverandiver.thesisproject.R.id.project_seekbarm;
import static com.lieverandiver.thesisproject.R.id.quiz_seekbarf;
import static com.lieverandiver.thesisproject.R.id.quiz_seekbarm;

public class CriteriaFinaltermInputActivity extends AppCompatActivity implements
        View.OnClickListener, SeekBar.OnSeekBarChangeListener, CompoundButton
        .OnCheckedChangeListener {

    private TextView txtActivityPercent;
    private TextView txtAssignmentPercent;
    private TextView txtAttendancePercent;
    private TextView txtExamPercent;
    private TextView txtProjectPercent;
    private TextView txtQuizPercent;

    private SeekBar sbActivity;
    private SeekBar sbAssignment;
    private SeekBar sbAttendance;
    private SeekBar sbExam;
    private SeekBar sbProject;
    private SeekBar sbQuiz;

    private Switch swActivity;
    private Switch swAssignment;
    private Switch swAttendance;
    private Switch swExam;
    private Switch swProject;
    private Switch swQuiz;

    private LinearLayout laActivity;
    private LinearLayout laAssignment;
    private LinearLayout laAttendance;
    private LinearLayout laExam;
    private LinearLayout laProject;
    private LinearLayout laQuiz;

    private TextView txtSubjectName;
    private TextView txtTotalPercent;
    private LinearLayout laSave;
    private boolean isExist;
    private Spinner spinnerm;

    private int percent[] = new int[6];
    private String[] values = new String[]{
            "10%","20%","30%","40%","50%","60%","70%","80%","90%","100%"
    };

    private final SubjectService subjectService = new SubjectServiceImpl();
    private final FormulaService formulaService = new FormulaServiceImpl();
    private Subject subject;
    private Teacher teacher;
    private Formula formula;

    private void init() {

        laSave = (LinearLayout) findViewById(R.id.finals_save);
        txtSubjectName = (TextView) findViewById(R.id.finals_subjectname);
        txtTotalPercent = (TextView) findViewById(R.id.total_percentf);
        spinnerm = (Spinner) findViewById(R.id.finals_spinner_percent);

        sbActivity = (SeekBar) findViewById(activity_seekbarf);
        sbAssignment = (SeekBar) findViewById(assignment_seekbarf);
        sbAttendance = (SeekBar) findViewById(attendance_seekbarf);
        sbExam = (SeekBar) findViewById(exam_seekbarf);
        sbProject = (SeekBar) findViewById(project_seekbarf);
        sbQuiz = (SeekBar) findViewById(quiz_seekbarf);

        txtActivityPercent = (TextView) findViewById(R.id.activity_percent_textf);
        txtAssignmentPercent = (TextView) findViewById(R.id.assignment_percent_textf);
        txtAttendancePercent = (TextView) findViewById(R.id.attendance_percent_textf);
        txtExamPercent = (TextView) findViewById(R.id.exam_percent_textf);
        txtProjectPercent = (TextView) findViewById(R.id.project_percent_textf);
        txtQuizPercent = (TextView) findViewById(R.id.quiz_percent_textf);

        swActivity = (Switch) findViewById(activity_switch_redf);
        swAssignment = (Switch) findViewById(assignment_switch_redf);
        swAttendance = (Switch) findViewById(attendance_switch_redf);
        swExam = (Switch) findViewById(exam_switch_redf);
        swProject = (Switch) findViewById(R.id.project_switch_redf);
        swQuiz = (Switch) findViewById(R.id.quiz_switch_redf);

        sbActivity.setMax(100);
        sbAssignment.setMax(100);
        sbAttendance.setMax(100);
        sbExam.setMax(100);
        sbProject.setMax(100);
        sbQuiz.setMax(100);

        laActivity = (LinearLayout)findViewById(R.id.activty_linearf);
        laAssignment = (LinearLayout)findViewById(R.id.assignment_linearf);
        laAttendance = (LinearLayout)findViewById(R.id.attendance_linearf);
        laExam = (LinearLayout)findViewById(R.id.exam_linearf);
        laProject = (LinearLayout)findViewById(R.id.project_linearf);
        laQuiz = (LinearLayout)findViewById(R.id.quiz_linearf);

        laActivity.setVisibility(View.GONE);
        laAssignment.setVisibility(View.GONE);
        laAttendance.setVisibility(View.GONE);
        laExam.setVisibility(View.GONE);
        laProject.setVisibility(View.GONE);
        laQuiz.setVisibility(View.GONE);

        laSave.setOnClickListener(this);

        sbActivity.setOnSeekBarChangeListener(this);
        sbAssignment.setOnSeekBarChangeListener(this);
        sbAttendance.setOnSeekBarChangeListener(this);
        sbExam.setOnSeekBarChangeListener(this);
        sbProject.setOnSeekBarChangeListener(this);
        sbQuiz.setOnSeekBarChangeListener(this);

        swActivity.setOnCheckedChangeListener(this);
        swAssignment.setOnCheckedChangeListener(this);
        swAttendance.setOnCheckedChangeListener(this);
        swExam.setOnCheckedChangeListener(this);
        swProject.setOnCheckedChangeListener(this);
        swQuiz.setOnCheckedChangeListener(this);

        txtSubjectName.setText(subject.getName());

        if(isExist) {
            if(formula.getActivityPercentage() > 0)
                swActivity.setChecked(true);
            if(formula.getAssignmentPercentage() > 0)
                swAssignment.setChecked(true);
            if(formula.getAttendancePercentage() > 0)
                swAttendance.setChecked(true);
            if(formula.getExamPercentage() > 0)
                swExam.setChecked(true);
            if(formula.getProjectPercentage() > 0)
                swProject.setChecked(true);
            if(formula.getQuizPercentage() > 0)
                swQuiz.setChecked(true);

            sbActivity.setProgress(formula.getActivityPercentage());
            sbAssignment.setProgress(formula.getAssignmentPercentage());
            sbAttendance.setProgress(formula.getAttendancePercentage());
            sbExam.setProgress(formula.getExamPercentage());
            sbProject.setProgress(formula.getProjectPercentage());
            sbQuiz.setProgress(formula.getQuizPercentage());
        }else {
            sbActivity.setProgress(0);
            sbAssignment.setProgress(0);
            sbAttendance.setProgress(0);
            sbExam.setProgress(0);
            sbProject.setProgress(0);
            sbQuiz.setProgress(0);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_gradingfactor_activity_finals);

        try {
            subject = subjectService.getSubjectById(getIntent().getExtras().getLong("subjectId"));
            formula = formulaService.getFormulaById(getIntent().getExtras().getLong("formulaId"));
            teacher = new TeacherHelper(this).loadUser().get();
            isExist = getIntent().getExtras().getBoolean("isExist");

            init();

            ArrayAdapter<String> spinnerAdapter =
                    new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, values);
            spinnerm.setAdapter(spinnerAdapter);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        Formula _formula = new Formula();
        _formula.setActivityPercentage(percent[0]);
        _formula.setAssignmentPercentage(percent[1]);
        _formula.setAttendancePercentage(percent[2]);
        _formula.setExamPercentage(percent[3]);
        _formula.setProjectPercentage(percent[4]);
        _formula.setQuizPercentage(percent[5]);

        try {
            if(!isExist)
                _formula = new FormulaServiceImpl().addFormula(_formula, subject.getId(),
                        teacher.getId(), 2);
            else
                _formula = new FormulaServiceImpl().updateFormulaById(formula.getId(), _formula,
                        0, 0, 0);
        } catch (GradingFactorException e) {
            e.printStackTrace();
        }
        if (_formula != null) {
            getIntent().setClass(this, SubjectViewActivity.class);
            startActivity(getIntent());
            finish();
        } else
            Toast.makeText(CriteriaFinaltermInputActivity.this, "Please try again",
                    Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case activity_seekbarf :

                int x = 0;
                for(int count=0; count < percent.length; count++) {
                    if(count == 0)
                        continue;
                    x += percent[count];
                }
                if((x + progress) <= 100) {
                    percent[0] = progress;
                    txtActivityPercent.setText(String.valueOf(progress));;
                    txtTotalPercent.setText(x + percent[0] + "%");
                }else if((x + progress) > 100) {
                    percent[0] = 100-x;
                    txtActivityPercent.setText(String.valueOf(percent[0]));;
                    txtTotalPercent.setText(String.valueOf(x + percent[0]));
                    seekBar.setProgress(percent[0]);
                }else
                    percent[0] = 0;

                break;
            case assignment_seekbarf :

                x = 0;
                for(int count=0; count < percent.length; count++) {
                    if(count == 1)
                        continue;
                    x += percent[count];
                }
                if((x + progress) <= 100) {
                    percent[1] = progress;
                    txtAssignmentPercent.setText(progress + "");;
                    txtTotalPercent.setText(x + percent[1] + "%");
                }else if((x + progress) > 100) {
                    percent[1] = 100-x;
                    txtAssignmentPercent.setText(percent[1] + "");;
                    txtTotalPercent.setText((x + percent[1]) + "%");
                    seekBar.setProgress(percent[1]);
                }else
                    percent[1] = 0;

                break;
            case attendance_seekbarf :

                x = 0;
                for(int count=0; count < percent.length; count++) {
                    if(count == 2)
                        continue;
                    x += percent[count];
                }
                if((x + progress) <= 100) {
                    percent[2] = progress;
                    txtAttendancePercent.setText(progress + "");;
                    txtTotalPercent.setText(x + percent[2] + "%");
                }else if((x + progress) > 100) {
                    percent[2] = 100-x;
                    txtAttendancePercent.setText(percent[2] + "");;
                    txtTotalPercent.setText((x + percent[2]) + "%");
                    seekBar.setProgress(percent[2]);
                }else
                    percent[2] = 0;

                break;
            case exam_seekbarf :

                x = 0;
                for(int count=0; count < percent.length; count++) {
                    if(count == 3)
                        continue;
                    x += percent[count];
                }
                if((x + progress) <= 100) {
                    percent[3] = progress;
                    txtExamPercent.setText(progress + "");;
                    txtTotalPercent.setText(x + percent[3] + "%");
                }else if((x + progress) > 100) {
                    percent[3] = 100-x;
                    txtExamPercent.setText(percent[3] + "");;
                    txtTotalPercent.setText((x + percent[3]) + "%");
                    seekBar.setProgress(percent[3]);
                }else
                    percent[3] = 0;

                break;
            case project_seekbarf :

                x = 0;
                for(int count=0; count < percent.length; count++) {
                    if(count == 4)
                        continue;
                    x += percent[count];
                }
                if((x + progress) <= 100) {
                    percent[4] = progress;
                    txtProjectPercent.setText(progress + "");;
                    txtTotalPercent.setText(x + percent[4] + "%");
                }else if((x + progress) > 100) {
                    percent[4] = 100-x;
                    txtProjectPercent.setText(percent[4] + "");;
                    txtTotalPercent.setText((x + percent[4]) + "%");
                    seekBar.setProgress(percent[4]);
                }else
                    percent[4] = 0;

                break;
            case quiz_seekbarf :

                x = 0;
                for(int count=0; count < percent.length; count++) {
                    if(count == 5)
                        continue;
                    x += percent[count];
                }
                if((x + progress) <= 100) {
                    percent[5] = progress;
                    txtQuizPercent.setText(progress + "");;
                    txtTotalPercent.setText(x + percent[5] + "%");
                }else if((x + progress) > 100) {
                    percent[5] = 100-x;
                    txtQuizPercent.setText(percent[5] + "");;
                    txtTotalPercent.setText((x + percent[5]) + "%");
                    seekBar.setProgress(percent[5]);
                }else
                    percent[5] = 0;

                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()) {
            case activity_switch_redf :
                if (isChecked)
                    laActivity.setVisibility(View.VISIBLE);
                else
                    laActivity.setVisibility(View.GONE);

                int x = 0;
                percent[0] = 0;
                for(int i=0; i < percent.length; i++)
                    x += percent[i];
                txtTotalPercent.setText(x + "%");
                sbActivity.setProgress(0);
                break;
            case assignment_switch_redf :
                if (isChecked)
                    laAssignment.setVisibility(View.VISIBLE);
                else
                    laAssignment.setVisibility(View.GONE);

                x = 0;
                percent[1] = 0;
                for(int i=0; i < percent.length; i++)
                    x += percent[i];
                txtTotalPercent.setText(x + "%");
                sbAssignment.setProgress(0);
                break;
            case attendance_switch_redf :
                if (isChecked)
                    laAttendance.setVisibility(View.VISIBLE);
                else
                    laAttendance.setVisibility(View.GONE);

                x = 0;
                percent[2] = 0;
                for(int i=0; i < percent.length; i++)
                    x += percent[i];
                txtTotalPercent.setText(x + "%");
                sbAttendance.setProgress(0);
                break;
            case exam_switch_redf :
                if (isChecked)
                    laExam.setVisibility(View.VISIBLE);
                else
                    laExam.setVisibility(View.GONE);

                x = 0;
                percent[3] = 0;
                for(int i=0; i < percent.length; i++)
                    x += percent[i];
                txtTotalPercent.setText(x + "%");
                sbExam.setProgress(0);
                break;
            case project_seekbarf :
                if (isChecked)
                    laProject.setVisibility(View.VISIBLE);
                else
                    laProject.setVisibility(View.GONE);

                x = 0;
                percent[4] = 0;
                for(int i=0; i < percent.length; i++)
                    x += percent[i];
                txtTotalPercent.setText(x + "%");
                sbProject.setProgress(0);
                break;
            case quiz_seekbarf :
                if (isChecked)
                    laQuiz.setVisibility(View.VISIBLE);
                else
                    laQuiz.setVisibility(View.GONE);

                x = 0;
                percent[5] = 0;
                for(int i=0; i < percent.length; i++)
                    x += percent[i];
                txtTotalPercent.setText(x + "%");
                sbQuiz.setProgress(0);
                break;
        }
    }
}

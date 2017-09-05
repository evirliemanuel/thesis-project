package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.lieverandiver.thesisproject.helper.TeacherHelper;
import com.remswork.project.alice.exception.GradingFactorException;
import com.remswork.project.alice.model.Formula;
import com.remswork.project.alice.model.Subject;
import com.remswork.project.alice.model.Teacher;
import com.remswork.project.alice.service.FormulaService;
import com.remswork.project.alice.service.SubjectService;
import com.remswork.project.alice.service.impl.FormulaServiceImpl;
import com.remswork.project.alice.service.impl.SubjectServiceImpl;

import java.util.ArrayList;

import static com.lieverandiver.thesisproject.R.id.activity_seekbarm;
import static com.lieverandiver.thesisproject.R.id.activity_switch_redm;
import static com.lieverandiver.thesisproject.R.id.assignment_seekbarm;
import static com.lieverandiver.thesisproject.R.id.assignment_switch_redm;
import static com.lieverandiver.thesisproject.R.id.attendance_seekbarm;
import static com.lieverandiver.thesisproject.R.id.attendance_switch_redm;
import static com.lieverandiver.thesisproject.R.id.exam_seekbarm;
import static com.lieverandiver.thesisproject.R.id.exam_switch_redm;
import static com.lieverandiver.thesisproject.R.id.project_seekbarm;
import static com.lieverandiver.thesisproject.R.id.quiz_seekbarf;
import static com.lieverandiver.thesisproject.R.id.quiz_seekbarm;

public class CriteriaMidtermInputActivity extends AppCompatActivity implements RecognitionListener,
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

    //private TextView returnedText;
    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "VoiceRecognition";

    private void init() {

        laSave = (LinearLayout) findViewById(R.id.midterm_save);
        txtSubjectName = (TextView) findViewById(R.id.midterm_subjectname);
        txtTotalPercent = (TextView) findViewById(R.id.total_percentm);
        spinnerm = (Spinner) findViewById(R.id.midterm_spinner_percent);

        sbActivity = (SeekBar) findViewById(activity_seekbarm);
        sbAssignment = (SeekBar) findViewById(R.id.assignment_seekbarm);
        sbAttendance = (SeekBar) findViewById(R.id.attendance_seekbarm);
        sbExam = (SeekBar) findViewById(exam_seekbarm);
        sbProject = (SeekBar) findViewById(project_seekbarm);
        sbQuiz = (SeekBar) findViewById(quiz_seekbarm);

        txtActivityPercent = (TextView) findViewById(R.id.activity_percent_textm);
        txtAssignmentPercent = (TextView) findViewById(R.id.assignment_percent_textm);
        txtAttendancePercent = (TextView) findViewById(R.id.attendance_percent_textm);
        txtExamPercent = (TextView) findViewById(R.id.exam_percent_textm);
        txtProjectPercent = (TextView) findViewById(R.id.project_percent_textm);
        txtQuizPercent = (TextView) findViewById(R.id.quiz_percent_textm);

        swActivity = (Switch) findViewById(activity_switch_redm);
        swAssignment = (Switch) findViewById(R.id.assignment_switch_redm);
        swAttendance = (Switch) findViewById(R.id.attendance_switch_redm);
        swExam = (Switch) findViewById(R.id.exam_switch_redm);
        swProject = (Switch) findViewById(R.id.project_switch_redm);
        swQuiz = (Switch) findViewById(R.id.quiz_switch_redm);

        sbActivity.setMax(100);
        sbAssignment.setMax(100);
        sbAttendance.setMax(100);
        sbExam.setMax(100);
        sbProject.setMax(100);
        sbQuiz.setMax(100);

        laActivity = (LinearLayout)findViewById(R.id.activty_linearm);
        laAssignment = (LinearLayout)findViewById(R.id.assignment_linearm);
        laAttendance = (LinearLayout)findViewById(R.id.attendance_linearm);
        laExam = (LinearLayout)findViewById(R.id.exam_linearm);
        laProject = (LinearLayout)findViewById(R.id.project_linearm);
        laQuiz = (LinearLayout)findViewById(R.id.quiz_linearm);

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

        //returnedText = (TextView) findViewById(R.id.textView1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton1);

        progressBar.setVisibility(View.INVISIBLE);
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,"en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,this.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(true);
                    speech.startListening(recognizerIntent);
                } else {
                    progressBar.setIndeterminate(false);
                    progressBar.setVisibility(View.INVISIBLE);
                    speech.stopListening();
                }
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_gradingfactor_activity_midterm);

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
                        teacher.getId(), 1);
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
            Toast.makeText(CriteriaMidtermInputActivity.this, "Please try again",
                    Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case activity_seekbarm :

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
            case assignment_seekbarm :

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
            case attendance_seekbarm :

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
            case exam_seekbarm :

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
            case project_seekbarm :

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
            case quiz_seekbarm :

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
            case activity_switch_redm :
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
            case assignment_switch_redm :
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
            case attendance_switch_redm :
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
            case exam_switch_redm :
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
            case project_seekbarm :
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

    @Override
    protected void onPause() {
        super.onPause();
        if (speech != null) {
            speech.destroy();
            Log.i(LOG_TAG, "destroy");
        }

    }

    @Override
    public void onBeginningOfSpeech() {
        Log.i(LOG_TAG, "onBeginningOfSpeech");
        progressBar.setIndeterminate(false);
        progressBar.setMax(10);
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i(LOG_TAG, "onBufferReceived: " + buffer);
    }

    @Override
    public void onEndOfSpeech() {
        Log.i(LOG_TAG, "onEndOfSpeech");
        progressBar.setIndeterminate(true);
        toggleButton.setChecked(false);
    }

    @Override
    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);
        Log.d(LOG_TAG, "FAILED " + errorMessage);
        //returnedText.setText(errorMessage);
        txtActivityPercent.setText(errorMessage);
        toggleButton.setChecked(false);
    }

    @Override
    public void onEvent(int arg0, Bundle arg1) {
        Log.i(LOG_TAG, "onEvent");
    }

    @Override
    public void onPartialResults(Bundle arg0) {
        Log.i(LOG_TAG, "onPartialResults");
    }

    @Override
    public void onReadyForSpeech(Bundle arg0) {
        Log.i(LOG_TAG, "onReadyForSpeech");
    }

    @Override
    public void onResults(Bundle results) {
        Log.i(LOG_TAG, "onResults");
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String text = "";
        for (String result : matches) {
            try {
                Integer.parseInt(result);
                text = result;
                break;
            }catch (NumberFormatException e) {
                e.printStackTrace();
                text = "0";
            }
        }
        //returnedText.setText(text);

        txtActivityPercent.setText(text);
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i(LOG_TAG, "onRmsChanged: " + rmsdB);
        progressBar.setProgress((int) rmsdB);
    }

    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }
}

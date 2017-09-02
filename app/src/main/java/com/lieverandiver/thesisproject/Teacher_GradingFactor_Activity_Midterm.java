package com.lieverandiver.thesisproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.lieverandiver.thesisproject.helper.TeacherHelper;
import com.remswork.project.alice.exception.SubjectException;
import com.remswork.project.alice.model.Subject;
import com.remswork.project.alice.model.Teacher;
import com.remswork.project.alice.service.impl.SubjectServiceImpl;

/**
 * Created by Verlie on 9/1/2017.
 */

public class Teacher_GradingFactor_Activity_Midterm extends AppCompatActivity{

    private TextView textView1 ;
    private TextView textView2 ;
    private TextView textView3 ;
    private TextView textView4 ;
    private TextView textView5 ;
    private TextView textView6 ;

    private SeekBar seekBar1 ;
    private SeekBar seekBar2 ;
    private SeekBar seekBar3 ;
    private SeekBar seekBar4 ;
    private SeekBar seekBar5 ;
    private SeekBar seekBar6 ;

    int progress = 0;
    private int total;

    private Switch aSwitch1 ;
    private Switch aSwitch2 ;
    private Switch aSwitch3 ;
    private Switch aSwitch4 ;
    private Switch aSwitch5 ;
    private Switch aSwitch6 ;

    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private LinearLayout linearLayout3;
    private LinearLayout linearLayout4;
    private LinearLayout linearLayout5;
    private LinearLayout linearLayout6;

    private TextView textViewm1;
    private TextView textViewm2;
    private Spinner spinnerm;
    private  TextView textViewm3;
    private LinearLayout linearLayoutm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_gradingfactor_activity_midterm);

        Subject subject = new Subject();
        Teacher teacher = new Teacher();

        try {
            subject = new SubjectServiceImpl().getSubjectById(getIntent().getExtras().getLong("subjectId"));
            teacher = new TeacherHelper(this).loadUser().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        linearLayoutm = (LinearLayout) findViewById(R.id.midterm_save);

        textViewm1 = (TextView) findViewById(R.id.midterm_subjectname);
        textViewm2 = (TextView) findViewById(R.id.txtv_midterm_percent);
        spinnerm = (Spinner) findViewById(R.id.midterm_spinner_percent);
        textViewm3 = (TextView) findViewById(R.id.total_percentm);

        textViewm1.setText(subject.getName());


        seekBar1 = (SeekBar) findViewById(R.id.activity_seekbarm);
        seekBar1.setMax(100);
        seekBar1.setProgress(progress);

        seekBar2 = (SeekBar) findViewById(R.id.assignment_seekbarm);
        seekBar2.setMax(100);
        seekBar2.setProgress(progress);

        seekBar3 = (SeekBar) findViewById(R.id.attendance_seekbarm);
        seekBar3.setMax(100);
        seekBar3.setProgress(progress);

        seekBar4 = (SeekBar) findViewById(R.id.exam_seekbarm);
        seekBar4.setMax(100);
        seekBar4.setProgress(progress);

        seekBar5 = (SeekBar) findViewById(R.id.project_seekbarm);
        seekBar5.setMax(100);
        seekBar5.setProgress(progress);

        seekBar6 = (SeekBar) findViewById(R.id.quiz_seekbarm);
        seekBar6.setMax(100);
        seekBar6.setProgress(progress);



        textView1 = (TextView) findViewById(R.id.activity_percent_textm);
        textView2 = (TextView) findViewById(R.id.assignment_percent_textm);
        textView3 = (TextView) findViewById(R.id.attendance_percent_textm);
        textView4 = (TextView) findViewById(R.id.exam_percent_textm);
        textView5 = (TextView) findViewById(R.id.project_percent_textm);
        textView6 = (TextView) findViewById(R.id.quiz_percent_textm);



        aSwitch1 = (Switch) findViewById(R.id.activity_switch_redm);
        aSwitch2 = (Switch) findViewById(R.id.assignment_switch_redm);
        aSwitch3 = (Switch) findViewById(R.id.attendance_switch_redm);
        aSwitch4 = (Switch) findViewById(R.id.exam_switch_redm);
        aSwitch5 = (Switch) findViewById(R.id.project_switch_redm);
        aSwitch6 = (Switch) findViewById(R.id.quiz_switch_redm);

        linearLayout1 = (LinearLayout)findViewById(R.id.activty_linearm);
        linearLayout1.setVisibility(View.GONE);

        linearLayout2 = (LinearLayout)findViewById(R.id.assignment_linearm);
        linearLayout2.setVisibility(View.GONE);

        linearLayout3 = (LinearLayout)findViewById(R.id.attendance_linearm);
        linearLayout3.setVisibility(View.GONE);

        linearLayout4 = (LinearLayout)findViewById(R.id.exam_linearm);
        linearLayout4.setVisibility(View.GONE);

        linearLayout5 = (LinearLayout)findViewById(R.id.project_linearm);
        linearLayout5.setVisibility(View.GONE);

        linearLayout6 = (LinearLayout)findViewById(R.id.quiz_linearm);
        linearLayout6.setVisibility(View.GONE);


        aSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // do something when check is selected
                    linearLayout1.setVisibility(View.VISIBLE);
                } else {
                    //do something when unchecked
                    linearLayout1.setVisibility(View.GONE);

                }
            }
        });

        aSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // do something when check is selected
                    linearLayout2.setVisibility(View.VISIBLE);
                } else {
                    //do something when unchecked
                    linearLayout2.setVisibility(View.GONE);

                }
            }
        });

        aSwitch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // do something when check is selected
                    linearLayout3.setVisibility(View.VISIBLE);
                } else {
                    //do something when unchecked
                    linearLayout3.setVisibility(View.GONE);

                }
            }
        });

        aSwitch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // do something when check is selected
                    linearLayout4.setVisibility(View.VISIBLE);
                } else {
                    //do something when unchecked
                    linearLayout4.setVisibility(View.GONE);

                }
            }
        });

        aSwitch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // do something when check is selected
                    linearLayout5.setVisibility(View.VISIBLE);
                } else {
                    //do something when unchecked
                    linearLayout5.setVisibility(View.GONE);

                }
            }
        });

        aSwitch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // do something when check is selected
                    linearLayout6.setVisibility(View.VISIBLE);
                } else {
                    //do something when unchecked
                    linearLayout6.setVisibility(View.GONE);

                }
            }
        });


        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                textView1.setText("" + progress);
//                textView.setTextSize(progress);

                textViewm3.setText(
                        (seekBar1.getProgress() +
                seekBar2.getProgress())  + " %");


            }

            @Override
            public void onStartTrackingTouch(SeekBar bar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar bar) {
            }


        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                textView2.setText("" + progress);
//                textView.setTextSize(progress);

                textViewm3.setText(
                        (seekBar1.getProgress() +
                                seekBar2.getProgress())  + " %");


            }

            @Override
            public void onStartTrackingTouch(SeekBar bar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar bar) {
            }


        });

        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                textView3.setText("" + progress);
//                textView.setTextSize(progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar bar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar bar) {
            }


        });

        seekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                textView4.setText("" + progress);
//                textView.setTextSize(progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar bar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar bar) {
            }


        });

        seekBar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                textView5.setText("" + progress);
//                textView.setTextSize(progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar bar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar bar) {
            }


        });

        seekBar6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                textView6.setText("" + progress);
//                textView.setTextSize(progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar bar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar bar) {
            }


        });

    }

}

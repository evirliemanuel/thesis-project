package com.lieverandiver.thesisproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by Verlie on 9/2/2017.
 */

public class Teacher_GradingFactor_Activity_Finals extends AppCompatActivity {
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

    int progress = 24;

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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_gradingfactor_activity_finals);



        seekBar1 = (SeekBar) findViewById(R.id.activity_seekbar);
        seekBar1.setMax(100);
        seekBar1.setProgress(progress);

        seekBar2 = (SeekBar) findViewById(R.id.assignment_seekbar);
        seekBar2.setMax(100);
        seekBar2.setProgress(progress);

        seekBar3 = (SeekBar) findViewById(R.id.attendance_seekbar);
        seekBar3.setMax(100);
        seekBar3.setProgress(progress);

        seekBar4 = (SeekBar) findViewById(R.id.exam_seekbar);
        seekBar4.setMax(100);
        seekBar4.setProgress(progress);

        seekBar5 = (SeekBar) findViewById(R.id.project_seekbar);
        seekBar5.setMax(100);
        seekBar5.setProgress(progress);

        seekBar6 = (SeekBar) findViewById(R.id.quiz_seekbar);
        seekBar6.setMax(100);
        seekBar6.setProgress(progress);



        textView1 = (TextView) findViewById(R.id.activity_percent_text);
        textView2 = (TextView) findViewById(R.id.assignment_percent_text);
        textView3 = (TextView) findViewById(R.id.attendance_percent_text);
        textView4 = (TextView) findViewById(R.id.exam_percent_text);
        textView5 = (TextView) findViewById(R.id.project_percent_text);
        textView6 = (TextView) findViewById(R.id.quiz_percent_text);



        aSwitch1 = (Switch) findViewById(R.id.activity_switch_red);
        aSwitch2 = (Switch) findViewById(R.id.assignment_switch_red);
        aSwitch3 = (Switch) findViewById(R.id.attendance_switch_red);
        aSwitch4 = (Switch) findViewById(R.id.exam_switch_red);
        aSwitch5 = (Switch) findViewById(R.id.project_switch_red);
        aSwitch6 = (Switch) findViewById(R.id.quiz_switch_red);

        linearLayout1 = (LinearLayout)findViewById(R.id.activty_linear);
        linearLayout1.setVisibility(View.GONE);

        linearLayout2 = (LinearLayout)findViewById(R.id.assignment_linear);
        linearLayout2.setVisibility(View.GONE);

        linearLayout3 = (LinearLayout)findViewById(R.id.attendance_linear);
        linearLayout3.setVisibility(View.GONE);

        linearLayout4 = (LinearLayout)findViewById(R.id.exam_linear);
        linearLayout4.setVisibility(View.GONE);

        linearLayout5 = (LinearLayout)findViewById(R.id.project_linear);
        linearLayout5.setVisibility(View.GONE);

        linearLayout6 = (LinearLayout)findViewById(R.id.quiz_linear);
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



        //

    }

//    @Override
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        if (isChecked) {
//            // do something when check is selected
//            linearLayout.setVisibility(View.VISIBLE);
//        } else {
//            //do something when unchecked
//            linearLayout.setVisibility(View.INVISIBLE);
//        }
//    }
}
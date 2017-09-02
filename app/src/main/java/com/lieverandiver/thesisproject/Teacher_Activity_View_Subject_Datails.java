package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Verlie on 9/1/2017.
 */

public class Teacher_Activity_View_Subject_Datails  extends AppCompatActivity{

    private LinearLayout linearLayoutm;
    private LinearLayout linearLayoutf;

    private TextView textViewm;
    private TextView textViewf;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_activity_view_subject_datails);



        linearLayoutm = (LinearLayout)findViewById(R.id.midterm_setting);
        linearLayoutf = (LinearLayout)findViewById(R.id.finals_setting);


        linearLayoutm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Teacher_Activity_View_Subject_Datails.this,
                        Teacher_GradingFactor_Activity_Midterm.class);
                startActivity(intent);
            }
        });

        linearLayoutf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Teacher_Activity_View_Subject_Datails.this,
                        Teacher_GradingFactor_Activity_Finals.class);
                startActivity(intent);
            }
        });
    }
}

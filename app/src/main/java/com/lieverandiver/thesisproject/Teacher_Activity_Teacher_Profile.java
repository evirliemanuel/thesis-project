package com.lieverandiver.thesisproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class Teacher_Activity_Teacher_Profile extends AppCompatActivity {

    private TextView txtvName;
    private TextView txtvDept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_activity_view_teacher_profile);

        txtvName = (TextView)findViewById(R.id.txtv_fullname);
        txtvDept = (TextView)findViewById(R.id.txtv_dept)
        ;
    }
}

package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.lieverandiver.thesisproject.R.id.imageView;

public class Teacher_Activity_Teacher_Profile extends AppCompatActivity {

    private TextView txtvName;
    private TextView txtvDept;
    private Button buttonback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_activity_view_teacher_profile);

        txtvName = (TextView)findViewById(R.id.txtv_fullname);
        txtvDept = (TextView)findViewById(R.id.txtv_dept);
        buttonback = (Button)findViewById(R.id.back_button);

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Teacher_Activity_Teacher_Profile.this,
                        Home_Activity.class);
                startActivity(intent);
            }
        });
    }
}

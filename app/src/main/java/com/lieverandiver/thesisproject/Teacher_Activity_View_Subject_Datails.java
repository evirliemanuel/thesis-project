package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lieverandiver.thesisproject.adapter.SubjectAdapter;
import com.remswork.project.alice.exception.SubjectException;
import com.remswork.project.alice.model.Subject;
import com.remswork.project.alice.service.impl.SubjectServiceImpl;

/**
 * Created by Verlie on 9/1/2017.
 */

public class Teacher_Activity_View_Subject_Datails  extends AppCompatActivity {

    private LinearLayout linearLayoutm;
    private LinearLayout linearLayoutf;

    private TextView name;
    private TextView code;
    private TextView desc;
    private TextView unit;
    Subject subject = new Subject();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_activity_view_subject_datails);

        try {
            subject = new SubjectServiceImpl().getSubjectById(getIntent().getExtras().getLong("subjectId"));
        } catch (SubjectException e) {
            e.printStackTrace();
        }

        linearLayoutm = (LinearLayout)findViewById(R.id.midterm_setting);
        linearLayoutf = (LinearLayout)findViewById(R.id.finals_setting);


        name = (TextView) findViewById(R.id.a_class_f_view_subject_name);
        code = (TextView) findViewById(R.id.a_class_f_view_subject_code);
        desc = (TextView) findViewById(R.id.a_class_f_view_subject_desc);
        unit = (TextView) findViewById(R.id.a_class_f_view_subject_unit);

        name.setText(subject.getName());
        code.setText(subject.getCode());
        desc.setText(subject.getDescription());
        unit.setText(subject.getUnit() + "");

        linearLayoutm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Teacher_Activity_View_Subject_Datails.this,
                        Teacher_GradingFactor_Activity_Midterm.class);
                intent.putExtra("subjectId", subject.getId());
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

package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.lieverandiver.thesisproject.adapter.StudentAdapter2;
import com.remswork.project.alice.model.Activity;
import com.remswork.project.alice.model.Student;
import com.remswork.project.alice.service.ActivityService;
import com.remswork.project.alice.service.ClassService;
import com.remswork.project.alice.service.impl.ActivityServiceImpl;
import com.remswork.project.alice.service.impl.ClassServiceImpl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.lieverandiver.thesisproject.R.id.btn_backaddattendance;
import static com.lieverandiver.thesisproject.R.id.input_back1;
import static com.lieverandiver.thesisproject.R.id.input_ok1;
import static com.lieverandiver.thesisproject.R.id.relative_clicked3;
import static com.lieverandiver.thesisproject.R.id.view_schedule;
import static com.lieverandiver.thesisproject.R.id.view_student;
import static com.lieverandiver.thesisproject.R.id.viewactivitym;
import static com.lieverandiver.thesisproject.R.id.viewassignmentm;
import static com.lieverandiver.thesisproject.R.id.viewattendancem;

/**
 * Created by Verlie on 8/31/2017.
 */

public class Activity_A_Input_Activity extends AppCompatActivity implements View.OnClickListener {

    private final ClassService classService = new ClassServiceImpl();
    private final ActivityService activityService = new ActivityServiceImpl();
    List<Student> studentList = new ArrayList<>();
    private EditText editTextName;
    private TextView textViewDate;
    private ToggleButton buttonSubmit;
    private RecyclerView recyclerViewStudentInput;
    private EditText editTextTotal;
    private Button btnBack;
    private CardView dialogSucces;
    private CardView dialogFailed;
    private ToggleButton btnTryAgain;
    private Button btnOk;
    private StudentAdapter2 studentAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_z_input_activity);
        init();

        buttonSubmit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean isNoError=true;

                    Activity activity = new Activity();
                    activity.setTitle(editTextName.getText().toString());
                    activity.setDate(textViewDate.getText().toString());
                    activity.setItemTotal(Integer.parseInt(editTextTotal.getText().toString()));

                    studentAdapter.onValidate(activity.getItemTotal(), true);


                    if(studentAdapter.isNoError()) {
                        activity = activityService.addActivity(activity, getIntent().getExtras().getLong("classId"), 1L);
                        for(int i=0; i < studentList.size(); i++) {
                            int score = studentAdapter.getScore(i);
                            Student student = studentList.get(i);
                            activityService.addActivityResult(score, activity.getId(), student.getId());
                        }
                        dialogSucces.setVisibility(View.VISIBLE);
                        Toast.makeText(Activity_A_Input_Activity.this, "Success", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(Activity_A_Input_Activity.this, "Failed", Toast.LENGTH_LONG).show();
                     dialogFailed.setVisibility(View.VISIBLE);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case input_back1:
                Intent intent = getIntent().setClass(this, Activity_A_Add_Activity.class);
                startActivity(intent);
                break;
            case input_ok1:
               intent = getIntent().setClass(this, Activity_A_Add_Activity.class);
                startActivity(intent);
                break;

        }
    }

    public void init(){

        try {
            editTextName = (EditText) findViewById(R.id.input_name1);
            editTextTotal =(EditText) findViewById(R.id.input_total1);
            textViewDate = (TextView) findViewById(R.id.input_date1);
            buttonSubmit = (ToggleButton) findViewById(R.id.input_submit1);
            btnBack = (Button) findViewById(R.id.input_back1);
            dialogFailed = (CardView)findViewById(R.id.input_failed1);
            dialogSucces = (CardView)findViewById(R.id.input_succes1);
            btnOk = (Button) findViewById(R.id.input_ok1);
            btnTryAgain = (ToggleButton) findViewById(R.id.input_tryagain1);

            dialogSucces.setVisibility(View.GONE);
            dialogFailed.setVisibility(View.GONE);

            btnOk.setOnClickListener(this);
            btnBack.setOnClickListener(this);

            buttonSubmit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        buttonSubmit.setVisibility(View.GONE);

                    }
                }
            });

            btnTryAgain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        buttonSubmit.setChecked(false);
                        buttonSubmit.setVisibility(View.VISIBLE);
                        dialogFailed.setVisibility(View.GONE);

                    }
                }
            });

            recyclerViewStudentInput = (RecyclerView) findViewById(R.id.input_recyclerview1);

            for(Student s : classService.getStudentList(getIntent().getExtras().getLong("classId")))
                studentList.add(s);

            studentAdapter = new StudentAdapter2(this, studentList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            recyclerViewStudentInput.setAdapter(studentAdapter);
            recyclerViewStudentInput.setLayoutManager(layoutManager);
            recyclerViewStudentInput.setItemAnimator(new DefaultItemAnimator());

            String date = String.format(Locale.ENGLISH, "%02d/%02d/%d" , Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
                    Calendar.getInstance().get(Calendar.YEAR));
            textViewDate.setText(date);


        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public interface InputListener {
        void onValidate(int totalScore, boolean doValidate);
        boolean isNoError();
        int getScore(int index);
    }

}

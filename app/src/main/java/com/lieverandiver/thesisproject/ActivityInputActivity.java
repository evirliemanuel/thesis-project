package com.lieverandiver.thesisproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

/**
 * Created by Verlie on 8/31/2017.
 */

public class ActivityInputActivity extends AppCompatActivity{

    private final ClassService classService = new ClassServiceImpl();
    private final ActivityService activityService = new ActivityServiceImpl();
    List<Student> studentList = new ArrayList<>();
    private EditText editTextName;
    private TextView textViewDate;
    private Spinner spinner ;
    private TextView textViewTotal;
    private Button buttonSubmit;
    private RecyclerView recyclerViewStudentInput;
    private ImageView imageView;
    private EditText editTextTotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_grade_activity);

        init();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });

        buttonSubmit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean isNoError=true;
                    Activity activity = new Activity();
                    activity.setTitle(editTextName.getText().toString());
                    activity.setDate(textViewDate.getText().toString());
                    activity.setItemTotal(Integer.parseInt(editTextTotal.getText().toString()));
                    activity = activityService.addActivity(activity, getIntent().getExtras().getLong("classId"));

                    for(int i=0; i < studentList.size(); i++) {
                        RecyclerView.ViewHolder viewHolder = recyclerViewStudentInput.findViewHolderForAdapterPosition(i);
                        int score = ((StudentAdapter2.StudentAdapterViewHolder) viewHolder).getScore();
                        if(score > Integer.parseInt(editTextTotal.getText().toString())) {
                            ((StudentAdapter2.StudentAdapterViewHolder) viewHolder).setStatus(false);
                            isNoError=false;
                        }
                        else {
                            ((StudentAdapter2.StudentAdapterViewHolder) viewHolder).setStatus(true);
                        }
                    }

                    if(isNoError) {
                        for(int i=0; i < studentList.size(); i++) {
                            RecyclerView.ViewHolder viewHolder = recyclerViewStudentInput.findViewHolderForAdapterPosition(i);
                            int score = ((StudentAdapter2.StudentAdapterViewHolder) viewHolder).getScore();
                            Student student = ((StudentAdapter2.StudentAdapterViewHolder) viewHolder).getStudent();
                            activityService.addActivityResult(score, activity.getId(), student.getId());
                        }
                        Toast.makeText(ActivityInputActivity.this, "Success", Toast.LENGTH_LONG).show();
                    }else
                        Toast.makeText(ActivityInputActivity.this, "Failed", Toast.LENGTH_LONG).show();
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void init(){

        try {
            Log.i("Success : ", getIntent().getExtras().getLong("classId") + "");

            editTextName = (EditText) findViewById(R.id.etxt_name1);
            editTextTotal =(EditText) findViewById(R.id.etxt_totalm);
            textViewDate = (TextView) findViewById(R.id.txtv_date1);
//            spinner = (Spinner) findViewById(R.id.spiner_input1);
//            textViewTotal = (TextView) findViewById(R.id.txtv_total1);
            buttonSubmit = (Button) findViewById(R.id.btn_submit1);
            recyclerViewStudentInput = (RecyclerView) findViewById(R.id.recyclerview_view1);
            imageView = (ImageView) findViewById(R.id.btn_cancel1);

            for(Student s : classService.getStudentList(getIntent().getExtras().getLong("classId")))
                studentList.add(s);

            StudentAdapter2 studentAdapter = new StudentAdapter2(this, studentList);
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
}

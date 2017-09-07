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
import android.widget.TextView;
import android.widget.Toast;

import com.lieverandiver.thesisproject.adapter.StudentAdapter2;
import com.remswork.project.alice.model.Assignment;
import com.remswork.project.alice.model.Exam;
import com.remswork.project.alice.model.Student;
import com.remswork.project.alice.service.AssignmentService;
import com.remswork.project.alice.service.ClassService;
import com.remswork.project.alice.service.ExamService;
import com.remswork.project.alice.service.impl.AssignmentServiceImpl;
import com.remswork.project.alice.service.impl.ClassServiceImpl;
import com.remswork.project.alice.service.impl.ExamServiceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Verlie on 8/31/2017.
 */

public class ExamInputActivity extends AppCompatActivity{

    private final ClassService classService = new ClassServiceImpl();
    private final ExamService examService = new ExamServiceImpl();
    List<Student> studentList = new ArrayList<>();
    private EditText editTextName;
    private TextView textViewDate;
    private Button buttonSubmit;
    private RecyclerView recyclerViewStudentInput;
    private ImageView imageView;
    private EditText editTextTotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_grade_exam);

        init();

        buttonSubmit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean isNoError=true;
                    Exam exam = new Exam();
                    exam.setTitle(editTextName.getText().toString());
                    exam.setDate(textViewDate.getText().toString());
                    exam.setItemTotal(Integer.parseInt(editTextTotal.getText().toString()));
                    exam = examService.addExam(exam, getIntent().getExtras().getLong("classId"));

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
                            examService.addExamResult(score, exam.getId(), student.getId());
                        }
                        Toast.makeText(ExamInputActivity.this, "Success", Toast.LENGTH_LONG).show();
                    }else
                        Toast.makeText(ExamInputActivity.this, "Failed", Toast.LENGTH_LONG).show();
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void init(){

        try {
            Log.i("Success : ", getIntent().getExtras().getLong("classId") + "");

            editTextName = (EditText) findViewById(R.id.newid_name_exam);
            editTextTotal =(EditText) findViewById(R.id.newid_total_exam);
            textViewDate = (TextView) findViewById(R.id.newid_date_exam);
            buttonSubmit = (Button) findViewById(R.id.newid_submit_exam);
            recyclerViewStudentInput = (RecyclerView) findViewById(R.id.newid_recycle_exam);

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

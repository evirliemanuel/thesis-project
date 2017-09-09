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
import android.widget.TextView;
import android.widget.Toast;

import com.lieverandiver.thesisproject.adapter.AssignmentInputAdapter;
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

public class Activity_B_Input_Assignment extends AppCompatActivity{

    private final ClassService classService = new ClassServiceImpl();
    private final AssignmentService assignmentService = new AssignmentServiceImpl();
    List<Student> studentList = new ArrayList<>();
    private EditText editTextName;
    private TextView textViewDate;
    private Button buttonSubmit;
    private RecyclerView recyclerViewStudentInput;
    private EditText editTextTotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_z_input_assignment);

        init();

        buttonSubmit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean isNoError=true;
                    Assignment assignment = new Assignment();
                    assignment.setTitle(editTextName.getText().toString());
                    assignment.setDate(textViewDate.getText().toString());
                    assignment.setItemTotal(Integer.parseInt(editTextTotal.getText().toString()));
                    assignment = assignmentService.addAssignment(assignment, getIntent().getExtras().getLong("classId"));

                    for(int i=0; i < studentList.size(); i++) {
                        RecyclerView.ViewHolder viewHolder = recyclerViewStudentInput.findViewHolderForAdapterPosition(i);
                        int score = ((AssignmentInputAdapter.AssignmentViewHolder) viewHolder).getScore();
                        if(score > Integer.parseInt(editTextTotal.getText().toString())) {
                            ((AssignmentInputAdapter.AssignmentViewHolder) viewHolder).setStatus(false);
                            isNoError=false;
                        }
                        else {
                            ((AssignmentInputAdapter.AssignmentViewHolder) viewHolder).setStatus(true);
                        }
                    }

                    if(isNoError) {
                        for(int i=0; i < studentList.size(); i++) {
                            RecyclerView.ViewHolder viewHolder = recyclerViewStudentInput.findViewHolderForAdapterPosition(i);
                            int score = ((AssignmentInputAdapter.AssignmentViewHolder) viewHolder).getScore();
                            Student student = ((AssignmentInputAdapter.AssignmentViewHolder) viewHolder).getStudent();
                            assignmentService.addAssignmentResult(score, assignment.getId(), student.getId());
                        }
                        Toast.makeText(Activity_B_Input_Assignment.this, "Success", Toast.LENGTH_LONG).show();
                    }else
                        Toast.makeText(Activity_B_Input_Assignment.this, "Failed", Toast.LENGTH_LONG).show();
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void init(){

        try {
            Log.i("Success : ", getIntent().getExtras().getLong("classId") + "");

            editTextName = (EditText) findViewById(R.id.input_name2);
            editTextTotal =(EditText) findViewById(R.id.input_total2);
            textViewDate = (TextView) findViewById(R.id.input_date2);
            buttonSubmit = (Button) findViewById(R.id.input_submit2);
            recyclerViewStudentInput = (RecyclerView) findViewById(R.id.input_recyclerview2);

            for(Student s : classService.getStudentList(getIntent().getExtras().getLong("classId")))
                studentList.add(s);

            AssignmentInputAdapter assignmentAdapter = new AssignmentInputAdapter(this, studentList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            recyclerViewStudentInput.setAdapter(assignmentAdapter);
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

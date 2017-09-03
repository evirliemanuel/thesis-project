package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ToggleButton;

import com.lieverandiver.thesisproject.adapter.StudentAdapter;
import com.remswork.project.alice.exception.ClassException;
import com.remswork.project.alice.model.Student;
import com.remswork.project.alice.service.impl.ClassServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static android.R.id.toggle;

public class StudentViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnBack;
    private ToggleButton btnSearch;
    private Button btnSearchOk;
    private Button btnSearchCancel;
    private EditText editTextSearch;
    private FrameLayout frameLayoutSearch;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_view_student);
        recyclerView = (RecyclerView) findViewById(R.id.student_recyclerview);
        init(getIntent().getExtras().getLong("classId"));

        btnSearch = (ToggleButton)findViewById(R.id.btn_search_student);
        btnSearchOk = (Button)findViewById(R.id.btn_search_ok_student);
        btnBack = (Button) findViewById(R.id.btn_back_student);
        editTextSearch =(EditText)findViewById(R.id.etxt_search_student);
        frameLayoutSearch = (FrameLayout)findViewById(R.id.frame_search_student);

        frameLayoutSearch.setVisibility(View.GONE);


        btnSearch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    frameLayoutSearch.setVisibility(View.VISIBLE);
                } else {
                    frameLayoutSearch.setVisibility(View.GONE);
                }
            }
        });








    }

    public void init(long classId) {

        try {
            ClassServiceImpl classService =  new ClassServiceImpl();
            List<Student> studenList = new ArrayList<>();
            Set<Student> studentSet = classService.getStudentList(classId);

            for(Student student : studentSet)
                studenList.add(student);

            StudentAdapter studentAdapter = new StudentAdapter(this, studenList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setAdapter(studentAdapter);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        } catch (ClassException e) {
            e.printStackTrace();
        }
    }
}

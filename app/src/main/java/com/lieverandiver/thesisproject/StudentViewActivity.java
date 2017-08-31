package com.lieverandiver.thesisproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lieverandiver.thesisproject.adapter.StudentAdapter;
import com.remswork.project.alice.exception.ClassException;
import com.remswork.project.alice.model.Student;
import com.remswork.project.alice.service.impl.ClassServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StudentViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_view_student);
        recyclerView = (RecyclerView) findViewById(R.id.student_recyclerview);
        init(getIntent().getExtras().getLong("classId"));
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

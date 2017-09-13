package com.lieverandiver.thesisproject;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lieverandiver.thesisproject.adapter.GradeAdapter2;
import com.lieverandiver.thesisproject.helper.GradeHelper;
import com.remswork.project.alice.model.Grade;

import java.util.ArrayList;
import java.util.List;

public class GradeResultActivity extends AppCompatActivity{

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_result);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView = (RecyclerView) findViewById(R.id.grade_recycler_part);
        List<Grade> gradeList = new ArrayList<>();
        gradeList.add(new Grade(75));
        gradeList.add(new Grade(95));
        gradeList.add(new Grade(100));
        gradeList.add(new GradeHelper().computeGrade(1270, 462, 1));
        GradeAdapter2 gradeAdapter = new GradeAdapter2(getApplicationContext(), gradeList, 1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setAdapter(gradeAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}

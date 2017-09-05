package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static com.lieverandiver.thesisproject.R.id.imageView;

/**
 * Created by Verlie on 9/1/2017.
 */

public class Activity_Class_Add_Project extends AppCompatActivity{

    private Button buttonBack;
    private RecyclerView recyclerView;
    private LinearLayout linearLayoutProject;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_add_project);

        buttonBack = (Button) findViewById(R.id.btn_backaddproject);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
               finish();

            }
        });
        linearLayoutProject = (LinearLayout) findViewById(R.id.relative_clicked5) ;
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_view);

        linearLayoutProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Activity_Class_Add_Project.this,
                        Activity_Class_InputProject.class);
                startActivity(intent);
            }
        });

    }
}

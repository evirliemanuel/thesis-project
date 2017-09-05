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
 *
 */

public class Activity_Class_Add_Assignment extends AppCompatActivity{

    private RecyclerView recyclerView;
    private LinearLayout linearLayoutAssignment;
    private Button buttonABack;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_add_assignment);


       buttonABack = (Button)findViewById(R.id.btn_backassignment) ;
        buttonABack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
           finish();
            }
        });
        linearLayoutAssignment = (LinearLayout) findViewById(R.id.relative_clicked2) ;
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_view);

        linearLayoutAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Activity_Class_Add_Assignment.this,
                        Activity_Class_InputAssignment.class);
                startActivity(intent);
            }
        });



    }
}

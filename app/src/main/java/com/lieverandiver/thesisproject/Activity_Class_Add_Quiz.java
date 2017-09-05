package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static com.lieverandiver.thesisproject.R.id.imageView;

/**
 * Created by Verlie on 9/1/2017.
 */

public class Activity_Class_Add_Quiz extends AppCompatActivity{

    private ImageView imageView;
    private RecyclerView recyclerView;
    private LinearLayout linearLayoutQuiz;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_add_quiz);

        imageView = (ImageView)findViewById(R.id.img_back);
        linearLayoutQuiz = (LinearLayout) findViewById(R.id.relative_clicked6) ;
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_view);

        linearLayoutQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Activity_Class_Add_Quiz.this,
                        Activity_Class_InputQuiz.class);
                startActivity(intent);
            }
        });

    }
}

package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import static android.R.id.button1;

/**
 * Created by Verlie on 9/1/2017.
 */

public class Activity_Class_Add_Activity extends AppCompatActivity{

   private ImageView imageView;
    private RecyclerView recyclerView;
    private LinearLayout linearLayoutActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_add_activity);


        imageView = (ImageView)findViewById(R.id.img_back);
        linearLayoutActivity = (LinearLayout) findViewById(R.id.relative_clicked1) ;
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_view);

        linearLayoutActivity.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Activity_Class_Add_Activity.this,
                        Activity_Class_InputActivity.class);
                startActivity(intent);
            }
        });
    }

//    Intent i = new Intent(this, ActivityTwo.class);
//    startActivity(i);


}

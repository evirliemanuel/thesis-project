package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import static com.lieverandiver.thesisproject.R.id.imageView;

/**
 * Created by Verlie on 8/31/2017.
 */

public class Activity_Class_InputAttendance extends AppCompatActivity {

    private EditText editTextName;
    private TextView textViewDate;
    private Button buttonSelectAll ;
    private TextView textViewTotal;
    private Button buttonSubmit;
    private RecyclerView recyclerView;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_grade_attendance);

        editTextName = (EditText)findViewById(R.id.etxt_name3);
        textViewDate = (TextView)findViewById(R.id.txtv_date3);
        buttonSelectAll = (Button) findViewById(R.id.btn_selectall3);
        buttonSubmit = (Button)findViewById(R.id.btn_submit3_attendance);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_view);
        imageView =(ImageView)findViewById(R.id.btn_cancel) ;

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
              finish();
            }
        });
    }
}

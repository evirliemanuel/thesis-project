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

/**
 * Created by Verlie on 8/31/2017.
 */

public class Activity_Class_InputActivity extends AppCompatActivity{

    private EditText editTextName;
    private TextView textViewDate;
    private Spinner spinner ;
    private TextView textViewTotal;
    private Button buttonSubmit;
    private RecyclerView recyclerViewStudentInput;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_grade_activity);

      init();
    }

    public void init(){

        editTextName = (EditText)findViewById(R.id.etxt_name1);
        textViewDate = (TextView)findViewById(R.id.txtv_date1);
        spinner = (Spinner)findViewById(R.id.spiner_input1);
        textViewTotal = (TextView)findViewById(R.id.txtv_total1);
        buttonSubmit = (Button)findViewById(R.id.btn_submit1);
        recyclerViewStudentInput = (RecyclerView)findViewById(R.id.recyclerview_view1);
        imageView =(ImageView)findViewById(R.id.btn_cancel1) ;

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
               finish();
            }
        });
    }
}

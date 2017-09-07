package com.lieverandiver.thesisproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Verlie on 8/31/2017.
 */
@Deprecated
public class Activity_Class_View_Attendance extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewDate;
    private TextView textViewTotal;
    private RecyclerView recyclerViewAttendance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_view_attendance);


//        textViewName = (TextView)findViewById(R.id.name_attendance);
//        textViewDate = (TextView)findViewById(R.id.date_attendance);
//        textViewTotal = (TextView)findViewById(R.id.totalscore_attendance);
//        recyclerViewAttendance = (RecyclerView)findViewById(R.id.recyclerview_attendance);
    }

}

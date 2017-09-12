package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.lieverandiver.thesisproject.adapter.ActivityInputAdapter;
import com.lieverandiver.thesisproject.adapter.ActivityInputAdapterF;
import com.remswork.project.alice.model.Activity;
import com.remswork.project.alice.model.Student;
import com.remswork.project.alice.service.ActivityService;
import com.remswork.project.alice.service.ClassService;
import com.remswork.project.alice.service.impl.ActivityServiceImpl;
import com.remswork.project.alice.service.impl.ClassServiceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.lieverandiver.thesisproject.R.id.input_back1;
import static com.lieverandiver.thesisproject.R.id.input_ok1;
import static com.lieverandiver.thesisproject.R.id.input_tryagain1;
import static com.lieverandiver.thesisproject.R.id.input_tryagainemp1;

/**
 * Created by Verlie on 8/31/2017.
 */

public class ActivityInputActivityF extends AppCompatActivity implements View.OnClickListener {

    private final ClassService classService = new ClassServiceImpl();
    private final ActivityService activityService = new ActivityServiceImpl();
    List<Student> studentList = new ArrayList<>();
    private EditText editTextName;
    private TextView textViewDate;
    private ToggleButton buttonSubmit;
    private RecyclerView recyclerViewStudentInput;
    private EditText editTextTotal;
    private Button btnBack;
    private CardView dialogSucces;
    private CardView dialogFailed;
    private Button btnTryAgain;
    private Button btnOk;
    private ActivityInputAdapterF studentAdapter;
    private CardView getDialogEmptyTotal;
    private Button getBtnTryAgainEmptyTotal;

    private ToggleButton toggleButtonhideandshow;
    private FrameLayout frameLayouthideandshow;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_z_input_activity);
        init();

        buttonSubmit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Activity activity = new Activity();
                    activity.setTitle(!editTextName.getText().toString().trim().isEmpty() ?
                            editTextName.getText().toString().trim() : "Activity");
                    activity.setDate(textViewDate.getText().toString());

                    if (editTextTotal.getText().toString().matches("")) {
                        toggleButtonhideandshow.setChecked(false);
                        getDialogEmptyTotal.setVisibility(View.VISIBLE);
                        recyclerViewStudentInput.setVisibility(View.GONE);
                        return;
                    }else{
                        activity.setItemTotal(Integer.parseInt(editTextTotal.getText().toString()));
                    }


                    studentAdapter.setTotalItem(activity.getItemTotal());
                    studentAdapter.onValidate(true);

                    if(studentAdapter.isNoError()) {
                        activity = activityService.addActivity(activity, getIntent().getExtras().getLong("classId"), 2L);
                        for(int i=0; i < studentList.size(); i++) {
                            int score = studentAdapter.getScore(i);
                            Student student = studentList.get(i);
                            activityService.addActivityResult(score, activity.getId(), student.getId());
                        }
                        dialogSucces.setVisibility(View.VISIBLE);
                        Toast.makeText(ActivityInputActivityF.this, "Success", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(ActivityInputActivityF.this, "Failed", Toast.LENGTH_LONG).show();
                     dialogFailed.setVisibility(View.VISIBLE);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case input_back1:
                Intent intent = getIntent().setClass(this, ActivityAddActivity.class);
                startActivity(intent);
                break;
            case input_ok1:
               intent = getIntent().setClass(this, ActivityAddActivity.class);
                startActivity(intent);
                break;

            case input_tryagainemp1:

                buttonSubmit.setChecked(false);
                buttonSubmit.setVisibility(View.VISIBLE);
                getDialogEmptyTotal.setVisibility(View.GONE);
                recyclerViewStudentInput.setVisibility(View.VISIBLE);
                break;

            case input_tryagain1:
                buttonSubmit.setChecked(false);
                buttonSubmit.setVisibility(View.VISIBLE);
                dialogFailed.setVisibility(View.GONE);
                break;




        }

    }

    public void init(){

        try {
            editTextName = (EditText) findViewById(R.id.input_name1);
            editTextTotal =(EditText) findViewById(R.id.input_total1);
            textViewDate = (TextView) findViewById(R.id.input_date1);
            buttonSubmit = (ToggleButton) findViewById(R.id.input_submit1);
            btnBack = (Button) findViewById(R.id.input_back1);
            dialogFailed = (CardView)findViewById(R.id.input_failed1);
            dialogSucces = (CardView)findViewById(R.id.input_succes1);
            btnOk = (Button) findViewById(R.id.input_ok1);
            btnTryAgain = (Button) findViewById(input_tryagain1);
            getDialogEmptyTotal = (CardView) findViewById(R.id.input_failedemp1);
            getBtnTryAgainEmptyTotal =(Button) findViewById(R.id.input_tryagainemp1);

            toggleButtonhideandshow = (ToggleButton) findViewById(R.id.input_hideandshow1);
            frameLayouthideandshow = (FrameLayout)findViewById(R.id.input_detailts1);

            toggleButtonhideandshow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        frameLayouthideandshow.setVisibility(View.GONE);
                    }else{
                        frameLayouthideandshow.setVisibility(View.VISIBLE);
                    }
                }
            });

            getDialogEmptyTotal.setVisibility(View.GONE);
            dialogSucces.setVisibility(View.GONE);
            dialogFailed.setVisibility(View.GONE);

            btnOk.setOnClickListener(this);
            btnBack.setOnClickListener(this);

            buttonSubmit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        buttonSubmit.setVisibility(View.GONE);

                    }
                }
            });

            getBtnTryAgainEmptyTotal.setOnClickListener(this);
            btnTryAgain.setOnClickListener(this);

            recyclerViewStudentInput = (RecyclerView) findViewById(R.id.input_recyclerview1);
            recyclerViewStudentInput.setVisibility(View.VISIBLE);

            for(Student s : classService.getStudentList(getIntent().getExtras().getLong("classId")))
                studentList.add(s);

            studentAdapter = new ActivityInputAdapterF(this, studentList);
            recyclerViewStudentInput.setAdapter(studentAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            recyclerViewStudentInput.setLayoutManager(layoutManager);
            recyclerViewStudentInput.setItemAnimator(new DefaultItemAnimator());

            String date = String.format(Locale.ENGLISH, "%02d/%02d/%d" , Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
                    Calendar.getInstance().get(Calendar.YEAR));
            textViewDate.setText(date);


        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public interface InputListener {
        void onValidate(boolean doValidate);
        boolean isNoError();
        int getScore(int index);
        void setTotalItem(int score);
    }

}

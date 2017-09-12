package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lieverandiver.thesisproject.adapter.AttendanceInputAdapter;
import com.remswork.project.alice.model.Attendance;
import com.remswork.project.alice.model.Student;
import com.remswork.project.alice.service.AttendanceService;
import com.remswork.project.alice.service.ClassService;
import com.remswork.project.alice.service.impl.AttendanceServiceImpl;
import com.remswork.project.alice.service.impl.ClassServiceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.lieverandiver.thesisproject.R.id.btn_submit3_attendance;
import static com.lieverandiver.thesisproject.R.id.imageView;

public class AttendanceInputActivity extends AppCompatActivity {

    private final ClassService classService = new ClassServiceImpl();
    private final AttendanceService attendanceService = new AttendanceServiceImpl();
    private final List<Student> studentList = new ArrayList<>();
    private AttendanceInputAdapter attendanceInputAdapter;

    private TextView editTextName;
    private TextView textViewDate;
    private Button btnSelectAll;
    private Button buttonSubmit;
    private RecyclerView recyclerViewStudentInput;
    private CardView cardmessage;
    private Button btnBack;
    private TextView txMessageStatus;
    private RelativeLayout rlDisruptor;
    private CardView cardViewSucces;
    private CardView cardViewFailed;
    private Button btnTryagain;
    private Button btnOk;
    private CardView hidesomthing;


    private boolean toggleMark = true;
    private AttendanceInputListener listener;
    private long classId;
    private long termId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_z_input_attendace);

        classId = getIntent().getExtras().getLong("classId");
        termId = getIntent().getExtras().getLong("termId");

        init();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = getIntent().setClass(AttendanceInputActivity.this, AttendanceAddActivity.class);
                startActivity(intent);
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = getIntent().setClass(AttendanceInputActivity.this, AttendanceAddActivity.class);
                startActivity(intent);
            }
        });

        btnTryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                cardmessage.setVisibility(View.GONE);
                cardViewFailed.setVisibility(View.GONE);
                hidesomthing.setVisibility(View.VISIBLE);
            }
        });
        buttonSubmit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case btn_submit3_attendance:
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                new Handler(getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Attendance attendance = new Attendance();
                                            attendance.setTitle(!editTextName.getText().toString().trim().isEmpty() ?
                                                    editTextName.getText().toString().trim() : "Attendance");
                                            attendance.setDate(textViewDate.getText().toString());
                                            listener.onValidate();
                                            if (attendanceInputAdapter.getErrorCount() == 0) {
                                                rlDisruptor.setVisibility(View.VISIBLE);
                                                attendance = attendanceService.addAttendance(attendance, classId, termId);
                                                for (int i = 0; i < studentList.size(); i++) {
                                                    int status = attendanceInputAdapter.getStatus(i);
                                                    Student student = studentList.get(i);
                                                    attendanceService.addAttendanceResult(status, attendance.getId(), student.getId());
                                                }
                                                cardmessage.setVisibility(View.VISIBLE);
//                                                txMessageStatus.setText("Success");
                                                Toast.makeText(AttendanceInputActivity.this, "Success", Toast.LENGTH_LONG).show();
                                                cardViewSucces.setVisibility(View.VISIBLE);
                                            } else {
                                                cardmessage.setVisibility(View.VISIBLE);
                                                txMessageStatus.setText("Found " + attendanceInputAdapter.getErrorCount() + " errors");
                                                cardViewFailed.setVisibility(View.VISIBLE);
                                                hidesomthing.setVisibility(View.GONE);
                                            }

                                            rlDisruptor.setVisibility(View.GONE);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        }).start();
                        break;
                }
            }
        });
    }

    public void init() {
        try {
            hidesomthing = (CardView) findViewById(R.id.input_attendance_hide_save);
            editTextName = (TextView) findViewById(R.id.input_attendance_name);
            textViewDate = (TextView) findViewById(R.id.txtv_date3);
            btnBack = (Button) findViewById(R.id.input_back3);
            buttonSubmit = (Button) findViewById(btn_submit3_attendance);
            btnSelectAll = (Button) findViewById(R.id.btn_selectall3);
            recyclerViewStudentInput = (RecyclerView) findViewById(R.id.recyclerview_view3attendance);
            cardmessage = (CardView) findViewById(R.id.card_message_status);
            txMessageStatus = (TextView) findViewById(R.id.card_message_status_text);
            rlDisruptor = (RelativeLayout) findViewById(R.id.disruptor_loading);
            cardViewSucces = (CardView) findViewById(R.id.input_succes3);
            cardViewFailed = (CardView)findViewById(R.id.input_failed3);
            btnTryagain = (Button) findViewById(R.id.input_tryagain3);
            btnOk = (Button) findViewById(R.id.input_ok3);
            rlDisruptor.setVisibility(View.GONE);

            cardViewSucces.setVisibility(View.GONE);
            cardViewFailed.setVisibility(View.GONE);

            for (Student s : classService.getStudentList(classId))
                studentList.add(s);

            attendanceInputAdapter = new AttendanceInputAdapter(this, studentList);
            listener = (AttendanceInputListener) attendanceInputAdapter;
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            recyclerViewStudentInput.setAdapter(attendanceInputAdapter);
            recyclerViewStudentInput.setLayoutManager(layoutManager);
            recyclerViewStudentInput.setItemAnimator(new DefaultItemAnimator());

            String date = String.format(Locale.ENGLISH, "%02d/%02d/%d", Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
                    Calendar.getInstance().get(Calendar.YEAR));
            textViewDate.setText(date);

            btnSelectAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelect(toggleMark);
                    if (toggleMark) {
                        btnSelectAll.setText("All unselect");
                        toggleMark = false;
                    } else {
                        btnSelectAll.setText("All select");
                        toggleMark = true;
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface AttendanceInputListener {
        void onValidate();

        void onSelect(boolean isSelected);
    }
}

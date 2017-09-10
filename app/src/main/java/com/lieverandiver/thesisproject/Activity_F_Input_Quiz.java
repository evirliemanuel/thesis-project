package com.lieverandiver.thesisproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.lieverandiver.thesisproject.adapter.StudentAdapter2;
import com.remswork.project.alice.model.Quiz;
import com.remswork.project.alice.model.Student;
import com.remswork.project.alice.service.ClassService;
import com.remswork.project.alice.service.QuizService;
import com.remswork.project.alice.service.impl.ClassServiceImpl;
import com.remswork.project.alice.service.impl.QuizServiceImpl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Activity_F_Input_Quiz extends AppCompatActivity{

    private final ClassService classService = new ClassServiceImpl();
    private final QuizService quizService = new QuizServiceImpl();
    List<Student> studentList = new ArrayList<>();
    private EditText editTextName;
    private TextView textViewDate;
    private Spinner spinner ;
    private TextView textViewTotal;
    private Button buttonSubmit;
    private RecyclerView recyclerViewStudentInput;
    private EditText editTextTotal;

    private Button btnBack;
    private CardView dialogSucces;
    private CardView dialogFailed;
    private ToggleButton btnTryAgain;
    private Button btnOk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_z_input_quiz);

        init();
        buttonSubmit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean isNoError=true;
                    Quiz quiz = new Quiz();
                    quiz.setTitle(editTextName.getText().toString());
                    quiz.setDate(textViewDate.getText().toString());
                    quiz.setItemTotal(Integer.parseInt(editTextTotal.getText().toString()));
                    quiz = quizService.addQuiz(quiz, getIntent().getExtras().getLong("classId"));

                    for(int i=0; i < studentList.size(); i++) {
                        RecyclerView.ViewHolder viewHolder = recyclerViewStudentInput.findViewHolderForAdapterPosition(i);
                        int score = ((StudentAdapter2.StudentAdapterViewHolder) viewHolder).getScore();
                        if(score > Integer.parseInt(editTextTotal.getText().toString())) {
                            ((StudentAdapter2.StudentAdapterViewHolder) viewHolder).setStatus(false);
                            isNoError=false;
                        }
                        else {
                            ((StudentAdapter2.StudentAdapterViewHolder) viewHolder).setStatus(true);
                        }
                    }

                    if(isNoError) {
                        for(int i=0; i < studentList.size(); i++) {
                            RecyclerView.ViewHolder viewHolder = recyclerViewStudentInput.findViewHolderForAdapterPosition(i);
                            int score = ((StudentAdapter2.StudentAdapterViewHolder) viewHolder).getScore();
                            Student student = ((StudentAdapter2.StudentAdapterViewHolder) viewHolder).getStudent();
                            quizService.addQuizResult(score, quiz.getId(), student.getId());
                        }
                        Toast.makeText(Activity_F_Input_Quiz.this, "Success", Toast.LENGTH_LONG).show();
                    }else
                        Toast.makeText(Activity_F_Input_Quiz.this, "Failed", Toast.LENGTH_LONG).show();
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void init(){

        try {
            Log.i("Success : ", getIntent().getExtras().getLong("classId") + "");

            editTextName = (EditText) findViewById(R.id.input_name6);
            editTextTotal =(EditText) findViewById(R.id.input_total6);
            textViewDate = (TextView) findViewById(R.id.input_date6);
            buttonSubmit = (Button) findViewById(R.id.input_submit6);
            recyclerViewStudentInput = (RecyclerView) findViewById(R.id.input_recyclerview6);
            for(Student s : classService.getStudentList(getIntent().getExtras().getLong("classId")))
                studentList.add(s);

            StudentAdapter2 studentAdapter = new StudentAdapter2(this, studentList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            recyclerViewStudentInput.setAdapter(studentAdapter);
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
}

package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lieverandiver.thesisproject.adapter.QuizAdapter;
import com.remswork.project.alice.exception.GradingFactorException;
import com.remswork.project.alice.model.Quiz;
import com.remswork.project.alice.service.QuizService;
import com.remswork.project.alice.service.impl.QuizServiceImpl;

import java.util.List;
import static com.lieverandiver.thesisproject.R.id.btn_backaddexam;
import static com.lieverandiver.thesisproject.R.id.newid_quizl_clickednew;

public class QuizAddActivity extends AppCompatActivity implements QuizAdapter.OnClickListener,
        View.OnClickListener {

    private static final String TAG = QuizAddActivity.class.getSimpleName();

    final QuizService quizService = new QuizServiceImpl();
    private ImageView imageView;
    private Button btnBackButton;
    private RecyclerView recyclerView;
    private LinearLayout linearLayoutActivity;
    private long classId;
    private long termId;

    private class QuizAddThread extends Thread {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        List<Quiz> quizList = quizService.getQuizListByClassId(classId);
                        QuizAdapter quizAdapter = new QuizAdapter(QuizAddActivity.this, quizList);

                        LinearLayoutManager layoutManager = new LinearLayoutManager(QuizAddActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                        recyclerView.setAdapter(quizAdapter);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                    }catch (GradingFactorException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    private void init() {
        linearLayoutActivity = (LinearLayout) findViewById(newid_quizl_clickednew);
        recyclerView = (RecyclerView) findViewById(R.id.newid_quizr_clickednew);
        btnBackButton = (Button) findViewById(btn_backaddexam);

        linearLayoutActivity.setOnClickListener(this);
        btnBackButton.setOnClickListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_add_quiz);
        try {
            classId = getIntent().getExtras().getLong("classId");
            termId = getIntent().getExtras().getLong("termId");

            init();
            new QuizAddThread().start();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(Quiz quiz, long quizId) {
        Intent intent = getIntent();
        intent.putExtra("quizId", quizId);
        intent.setClass(this, ActivityResultViewActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case newid_quizl_clickednew :
                Intent intent = getIntent().setClass(this, QuizInputActivity.class);
                startActivity(intent);
                break;
            case btn_backaddexam :
                finish();
                break;
        }
    }
}

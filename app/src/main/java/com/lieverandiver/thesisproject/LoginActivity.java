package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lieverandiver.thesisproject.fragment.LoginFragment;
import com.lieverandiver.thesisproject.helper.TeacherHelper;
import com.remswork.project.alice.exception.TeacherException;
import com.remswork.project.alice.model.Teacher;
import com.remswork.project.alice.service.impl.TeacherServiceImpl;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener{

    private TeacherServiceImpl teacherService;
    private TeacherHelper teacherHelper;
    private ProgressBar progressBar;
    private FrameLayout screen;
    private boolean isVaild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        teacherService = new TeacherServiceImpl();
        teacherHelper = new TeacherHelper(this);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_login);
        screen = (FrameLayout) findViewById(R.id.white_screen_transparent);
        progressBar.setVisibility(View.INVISIBLE);
        screen.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (teacherHelper.loadUser().authenticate()) {
                Intent intent = new Intent(this, Home_Activity.class);
                intent.putExtra("teacherId", teacherHelper.loadUser().get().getId());
                startActivity(intent);
            }
        }catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doLogin(final String username, final String password) {
        Log.i("MyTAG", username + " " + password);
        progressBar.setVisibility(View.VISIBLE);
        screen.setVisibility(View.VISIBLE);
        //final boolean isVaild = false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    List<Teacher> teacherList = teacherService.getTeacherList();
                    for(Teacher teacher : teacherList ) {
                        if(teacher.getUserDetail().getUsername().equals(username.trim()) &&
                                teacher.getUserDetail().getPassword().equals(password.trim())) {
                            teacherHelper.saveUser(teacher.getId());
                            Intent intent = new Intent(LoginActivity.this, Home_Activity.class);
                            intent.putExtra("teacherId", teacher.getId());
                            isVaild = true;
                            startActivity(intent);
                        }
                    }
                } catch (TeacherException e) {
                    e.printStackTrace();
                }

                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if(!isVaild)
                            Toast.makeText(LoginActivity.this, "Incorrect username or password",
                                    Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        screen.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();
    }
}

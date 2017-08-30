package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        teacherService = new TeacherServiceImpl();
        teacherHelper = new TeacherHelper(this);
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
    public void doLogin(String username, String password) {
        Log.i("MyTAG", username + " " + password);
        try {
            boolean isVaild = false;
            List<Teacher> teacherList = teacherService.getTeacherList();
            for(Teacher teacher : teacherList ) {
                if(teacher.getUserDetail().getUsername().equals(username.trim()) &&
                        teacher.getUserDetail().getPassword().equals(password.trim())) {
                    teacherHelper.saveUser(teacher.getId());
                    Intent intent = new Intent(this, Home_Activity.class);
                    intent.putExtra("teacherId", teacher.getId());
                    isVaild = true;
                    startActivity(intent);
                }
            }
            if(!isVaild)
                Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
        } catch (TeacherException e) {
            e.printStackTrace();
        }
    }
}

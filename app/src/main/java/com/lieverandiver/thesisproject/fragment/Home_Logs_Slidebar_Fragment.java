package com.lieverandiver.thesisproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lieverandiver.thesisproject.R;
import com.lieverandiver.thesisproject.helper.TeacherHelper;
import com.remswork.project.alice.exception.TeacherException;
import com.remswork.project.alice.model.Teacher;
import com.remswork.project.alice.service.impl.TeacherServiceImpl;

import static com.lieverandiver.thesisproject.helper.TeacherHelper.teacher;

/**
 * Created by Verlie on 8/30/2017.
 */

public class Home_Logs_Slidebar_Fragment extends Fragment {

    private TextView textName;


    public Home_Logs_Slidebar_Fragment(){



    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        try {
//            Bundle bundle = getArguments();
 //           int teacherId = bundle.getInt("teacherId");
            Teacher teacher = TeacherHelper.getTeacher();

            Log.i("MyTAG", (teacher != null ? teacher.getFirstName() : "Error") );
            View view = inflater.inflate(R.layout.home_fragment_slidebar_logs, container, false);
            textName = (TextView) view.findViewById(R.id.text_full_name);
        textName.setText(teacher.getLastName() + " " + teacher.getFirstName() + " " + teacher.getMiddleName().toCharArray()[0] + ".");
            return view;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
    }
}


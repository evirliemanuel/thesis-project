package com.lieverandiver.thesisproject.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lieverandiver.thesisproject.R;
import com.lieverandiver.thesisproject.helper.TeacherHelper;
import com.remswork.project.alice.model.Teacher;

/**
 * Created by Verlie on 8/30/2017.
 */

public class Home_Logs_Slidebar_Fragment extends Fragment {

    private TextView textName;
    private LinearLayout viewLogout;
    private TeacherHelper teacherHelper;
    private Handler handler;


    public Home_Logs_Slidebar_Fragment(){



    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        handler = new Handler(getActivity().getMainLooper());
        View view = inflater.inflate(R.layout.home_fragment_slidebar_logs, container, false);
        textName = (TextView) view.findViewById(R.id.text_full_name);
        viewLogout = (LinearLayout) view.findViewById(R.id.btn_logout);
        viewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacherHelper.removeUser();
                getActivity().finish();
            }
        });
        displayEvenDelay();
        return view;
    }

    public void displayEvenDelay() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                teacherHelper = new TeacherHelper(getContext());
                final Teacher teacher = teacherHelper.loadUser().get();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textName.setText(teacher.getLastName() + " " + teacher.getFirstName() + " " +
                                teacher.getMiddleName().toCharArray()[0] + ".");
                    }
                });

            }
        }).start();
    }
}


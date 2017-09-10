//package com.lieverandiver.thesisproject;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.widget.ToggleButton;
//
//import com.lieverandiver.thesisproject.adapter.ProjectInputAdapter;
//import com.lieverandiver.thesisproject.adapter.StudentAdapter2;
//import com.remswork.project.alice.model.Exam;
//import com.remswork.project.alice.model.Project;
//import com.remswork.project.alice.model.Student;
//import com.remswork.project.alice.service.ClassService;
//import com.remswork.project.alice.service.ExamService;
//import com.remswork.project.alice.service.ProjectService;
//import com.remswork.project.alice.service.impl.ClassServiceImpl;
//import com.remswork.project.alice.service.impl.ExamServiceImpl;
//import com.remswork.project.alice.service.impl.ProjectServiceImpl;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//import java.util.Locale;
//
///**
// * Created by Verlie on 8/31/2017.
// */
//
//public class Activity_E_Input_Project extends AppCompatActivity{
//
//    private final ClassService classService = new ClassServiceImpl();
//    private final ProjectService projectService = new ProjectServiceImpl();
//    List<Student> studentList = new ArrayList<>();
//    private EditText editTextName;
//    private TextView textViewDate;
//    private Button buttonSubmit;
//    private RecyclerView recyclerViewStudentInput;
//    private EditText editTextTotal;
//
//    private Button btnBack;
//    private CardView dialogSucces;
//    private CardView dialogFailed;
//    private ToggleButton btnTryAgain;
//    private Button btnOk;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_z_input_project);
//
//        init();
//
//        buttonSubmit.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    boolean isNoError=true;
//                    Project project = new Project();
//                    project.setTitle(editTextName.getText().toString());
//                    project.setDate(textViewDate.getText().toString());
//                    project.setItemTotal(Integer.parseInt(editTextTotal.getText().toString()));
//                    project = projectService.addProject(project, getIntent().getExtras().getLong("classId"));
//
//                    for(int i=0; i < studentList.size(); i++) {
//                        RecyclerView.ViewHolder viewHolder = recyclerViewStudentInput.findViewHolderForAdapterPosition(i);
//                        int score = ((ProjectInputAdapter.ProjectViewHolder) viewHolder).getScore();
//                        if(score > Integer.parseInt(editTextTotal.getText().toString())) {
//                            ((ProjectInputAdapter.ProjectViewHolder) viewHolder).setStatus(false);
//                            isNoError=false;
//                        }
//                        else {
//                            ((ProjectInputAdapter.ProjectViewHolder) viewHolder).setStatus(true);
//                        }
//                    }
//
//                    if(isNoError) {
//                        for(int i=0; i < studentList.size(); i++) {
//                            RecyclerView.ViewHolder viewHolder = recyclerViewStudentInput.findViewHolderForAdapterPosition(i);
//                            int score = ((ProjectInputAdapter.ProjectViewHolder) viewHolder).getScore();
//                            Student student = ((ProjectInputAdapter.ProjectViewHolder) viewHolder).getStudent();
//                            projectService.addProjectResult(score, project.getId(), student.getId());
//                        }
//                        Toast.makeText(Activity_E_Input_Project.this, "Success", Toast.LENGTH_LONG).show();
//                    }else
//                        Toast.makeText(Activity_E_Input_Project.this, "Failed", Toast.LENGTH_LONG).show();
//                }catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//    }
//
//    public void init(){
//
//        try {
//            Log.i("Success : ", getIntent().getExtras().getLong("classId") + "");
//
//            editTextName = (EditText) findViewById(R.id.input_name5);
//            editTextTotal =(EditText) findViewById(R.id.input_total5);
//            textViewDate = (TextView) findViewById(R.id.input_date5);
//            buttonSubmit = (Button) findViewById(R.id.input_submit5);
//            recyclerViewStudentInput = (RecyclerView) findViewById(R.id.input_recyclerview5);
//
//            for(Student s : classService.getStudentList(getIntent().getExtras().getLong("classId")))
//                studentList.add(s);
//
//            ProjectInputAdapter projectAdapter = new ProjectInputAdapter(this, studentList);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//
//            recyclerViewStudentInput.setAdapter(projectAdapter);
//            recyclerViewStudentInput.setLayoutManager(layoutManager);
//            recyclerViewStudentInput.setItemAnimator(new DefaultItemAnimator());
//
//            String date = String.format(Locale.ENGLISH, "%02d/%02d/%d" , Calendar.getInstance().get(Calendar.MONTH),
//                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
//                    Calendar.getInstance().get(Calendar.YEAR));
//            textViewDate.setText(date);
//
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}

//package com.lieverandiver.thesisproject;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.widget.TextView;
//
//import com.lieverandiver.thesisproject.adapter.SimpleActivityAdapter;
//import com.lieverandiver.thesisproject.adapter.SimpleAssignmentAdapter;
//import com.remswork.project.alice.exception.ClassException;
//import com.remswork.project.alice.exception.GradingFactorException;
//import com.remswork.project.alice.model.Activity;
//import com.remswork.project.alice.model.ActivityResult;
//import com.remswork.project.alice.model.Assignment;
//import com.remswork.project.alice.model.AssignmentResult;
//import com.remswork.project.alice.model.Student;
//import com.remswork.project.alice.service.ActivityService;
//import com.remswork.project.alice.service.AssignmentService;
//import com.remswork.project.alice.service.ClassService;
//import com.remswork.project.alice.service.impl.ActivityServiceImpl;
//import com.remswork.project.alice.service.impl.AssignmentServiceImpl;
//import com.remswork.project.alice.service.impl.ClassServiceImpl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ActivityResultViewAssignment extends AppCompatActivity {
//
//    private static final String TAG = ActivityResultViewAssignment.class.getSimpleName();
//
//    private final AssignmentService assignmentService = new AssignmentServiceImpl();
//    private final ClassService classService = new ClassServiceImpl();
//    private final List<AssignmentResult> resultList = new ArrayList<>();
//    private Assignment assignment;
//
//    private TextView textViewDate;
//    private TextView textViewName;
//    private TextView textViewTotal;
//    private RecyclerView recyclerViewView;
//
//    private long classId;
//    private long termId;
//    private long activityId;
//
//    private class AssignmentViewThread extends Thread {
//        @Override
//        public void run() {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        String date = assignment.getDate();
//                        String title = assignment.getTitle();
//                        String itemTotal = String.valueOf(assignment.getItemTotal());
//
//                        textViewDate.setText(date);
//                        textViewName.setText(title);
//                        textViewTotal.setText(itemTotal);
//
//                        for (Student s : classService.getStudentList(classId)) {
//                            ActivityResult result = assignmentService.getAssignmentResultByActivityAndStudentId(assignment.getId(), s.getId());
//                            if(result != null)
//                                resultList.add(result);
//                        }
//
//                        SimpleActivityAdapter simpleAssignmentAdapter = new SimpleAssignmentAdapter(ActivityResultViewAssignment.this, resultList);
//                        LinearLayoutManager layoutManager = new LinearLayoutManager(ActivityResultViewAssignment.this);
//                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//
//                        recyclerViewView.setAdapter(simpleAssignmentAdapter);
//                        recyclerViewView.setLayoutManager(layoutManager);
//                        recyclerViewView.setItemAnimator(new DefaultItemAnimator());
//                    } catch (ClassException| GradingFactorException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//    }
//
//    public void init() {
//        textViewDate = (TextView) findViewById(R.id.date_assignment);
//        textViewName = (TextView) findViewById(R.id.name_assignment);
//        textViewTotal = (TextView) findViewById(R.id.totalscore_assignment);
//        recyclerViewView = (RecyclerView) findViewById(R.id.recyclerview_assignment);
//    }
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        try {
//            Log.i(TAG, "onCreate");
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_class_view_assignment);
//
//            classId = getIntent().getExtras().getLong("classId");
//            activityId = getIntent().getExtras().getLong("assignmentId");
//            termId = getIntent().getExtras().getLong("termId");
//            assignment = assignmentService.getAssignmentById(assignmentId);
//
//            init();
//            new AssignmentViewThread().start();
//        }catch (GradingFactorException e) {
//            e.printStackTrace();
//        }
//    }
//}

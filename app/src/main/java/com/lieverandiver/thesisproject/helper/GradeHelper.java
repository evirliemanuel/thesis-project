package com.lieverandiver.thesisproject.helper;

import android.util.Log;

import com.remswork.project.alice.exception.GradingFactorException;
import com.remswork.project.alice.model.Activity;
import com.remswork.project.alice.model.Class;
import com.remswork.project.alice.model.Formula;
import com.remswork.project.alice.model.Grade;
import com.remswork.project.alice.service.ActivityService;
import com.remswork.project.alice.service.ClassService;
import com.remswork.project.alice.service.FormulaService;
import com.remswork.project.alice.service.impl.ActivityServiceImpl;
import com.remswork.project.alice.service.impl.ClassServiceImpl;
import com.remswork.project.alice.service.impl.FormulaServiceImpl;

import java.util.List;

public class GradeHelper {

    final ActivityService activityService = new ActivityServiceImpl();
    final ClassService classService = new ClassServiceImpl();
    final FormulaService formulaService = new FormulaServiceImpl();

    private double activityGrade;
    private double assignmentGrade;
    private double attendanceGrade;
    private double examGrade;
    private double projectGrade;
    private double quizGrade;

    public GradeHelper() {

    }

    public List<Grade> getGradeList() {
        return null;
    }

    public Grade computeGrade(final long classId, final long studentId, final long termId) {
        try {

            final Class _class = classService.getClassById(classId);
//            final Formula formula = formulaService.getFormulaBySubjectAndTeacherId(
//                    _class.getSubject().getId(), _class.getTeacher().getId(), termId);
//            formula.setActivityPercentage(10);
            Formula formula = new Formula();
            formula.setActivityPercentage(10);
            formula.setActivityPercentage(15);
            formula.setAssignmentPercentage(15);
            formula.setQuizPercentage(15);
            formula.setExamPercentage(35);
            formula.setProjectPercentage(10);
            if(formula.getActivityPercentage() > 0) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final List<Activity> activityList = activityService.getActivityListByStudentId(studentId);
                            final double fActivity[] = new double[activityList.size()];
                            double tempTotal = 0;

                            for (int i = 0; i < activityList.size(); i++) {
                                if(activityList.get(i).get_class().getId() == classId) {
                                    final double total = activityList.get(i).getItemTotal();
                                    final double score = activityService
                                            .getActivityResultByActivityAndStudentId(activityList.get(i).getId(), studentId).getScore();
                                    fActivity[i] = (score / total) * 100;
                                    Log.i("Activity", fActivity[i] + "");
                                }
                            }
                            for(int i = 0; i < fActivity.length; i++)
                                tempTotal += fActivity[i];

                            tempTotal /= fActivity.length;
                            activityGrade = tempTotal;
                        } catch (GradingFactorException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
            Log.i("GRADING TAGGG", activityGrade + "");
            return new Grade(activityGrade);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}

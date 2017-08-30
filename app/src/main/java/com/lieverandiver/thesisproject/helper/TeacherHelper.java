package com.lieverandiver.thesisproject.helper;

import com.remswork.project.alice.model.Teacher;

/**
 * Created by Verlie on 8/30/2017.
 */

public class TeacherHelper {

    public static Teacher teacher;

    public static Teacher getTeacher() {
        return teacher;
    }

    public static void setTeacher(Teacher teacher) {
        TeacherHelper.teacher = teacher;
    }
}

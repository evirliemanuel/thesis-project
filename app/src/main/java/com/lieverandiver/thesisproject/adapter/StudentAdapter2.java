package com.lieverandiver.thesisproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.lieverandiver.thesisproject.R;
import com.remswork.project.alice.model.Student;

import java.util.List;
import java.util.Locale;


public class StudentAdapter2 extends RecyclerView.Adapter<StudentAdapter2.StudentAdapterViewHolder> {

    private List<Student> studentList;
    private Context context;
    private LayoutInflater layoutInflater;
    private int total;

    public StudentAdapter2(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public StudentAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.cardview_data_student_input_grade, parent, false);
        return new StudentAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentAdapterViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.setView(student, position);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class StudentAdapterViewHolder extends RecyclerView.ViewHolder {

        private ImageView studentImage;
        private TextView studentDetail;
        private EditText editText;
        private Student student;
        private Spinner spinner;
        private LinearLayout layout;

        StudentAdapterViewHolder(View itemView) {
            super(itemView);
            studentImage = (ImageView) itemView.findViewById(R.id.f_data_student_profile);
            studentDetail = (TextView) itemView.findViewById(R.id.input_studentname);
            editText = (EditText) itemView.findViewById(R.id.input_score);
            layout = (LinearLayout) itemView.findViewById(R.id.bg_la);
        }

        void setView(final Student student, final int position) {
            this.student = student;
            String display = String.format(Locale.ENGLISH, "%s \t%s %s. %s - %d",
                    "1-A",
                    student.getFirstName(),
                    student.getMiddleName().substring(0, 1),
                    student.getLastName(),
                    student.getStudentNumber());
            studentDetail.setText(display);
        }

        public Student getStudent() {
            return student;
        }

        public int getScore() {
            return Integer.parseInt(editText.getText().toString());
        }

        public void setStatus(boolean isSuccess) {
            if(isSuccess)
                layout.setBackgroundColor(context.getResources().getColor(R.color.colorLightSuccess));
            else
                layout.setBackgroundColor(context.getResources().getColor(R.color.colorLightDanger));
            studentDetail.setTextColor(context.getResources().getColor(R.color.colorMoca2));
        }
    }
}

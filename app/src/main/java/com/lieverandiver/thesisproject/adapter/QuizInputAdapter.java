package com.lieverandiver.thesisproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lieverandiver.thesisproject.R;
import com.remswork.project.alice.model.Student;

import java.util.List;
import java.util.Locale;


public class QuizInputAdapter extends RecyclerView.Adapter<QuizInputAdapter.QuizViewHolder> {

    private List<Student> studentList;
    private Context context;
    private LayoutInflater layoutInflater;
    private int total;

    public QuizInputAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_z_input_quiz_cardview, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuizViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.setView(student, position);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {

        private TextView studentDetail;
        private EditText editText;
        private Student student;
        private LinearLayout layout;

        QuizViewHolder(View itemView) {
            super(itemView);
            studentDetail = (TextView) itemView.findViewById(R.id.input_cardview_name6);
            editText = (EditText) itemView.findViewById(R.id.input_cardview_score6);
            layout = (LinearLayout) itemView.findViewById(R.id.input_cardview_layout6);
        }

        void setView(final Student student, final int position) {
            this.student = student;
            String display = String.format(Locale.ENGLISH, "%s, %s %s.",
                    student.getLastName(),
                    student.getFirstName(),
                    student.getMiddleName().substring(0, 1));
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

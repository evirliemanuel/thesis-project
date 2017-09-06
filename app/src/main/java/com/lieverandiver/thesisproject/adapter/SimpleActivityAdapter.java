package com.lieverandiver.thesisproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.lieverandiver.thesisproject.R;
import com.remswork.project.alice.model.ActivityResult;
import com.remswork.project.alice.model.Student;

import java.util.List;
import java.util.Locale;

public class SimpleActivityAdapter extends RecyclerView
        .Adapter<SimpleActivityAdapter.SimpleActivityViewHolder> {

    private LayoutInflater layoutInflater;
    private List<ActivityResult> resultList;
    private Context context;
    private ActivityAdapter.OnClickListener onClickListener;

    public SimpleActivityAdapter(Context context, List<ActivityResult> resultList) {
        layoutInflater = LayoutInflater.from(context);
        this.resultList = resultList;
    }

    @Override
    public SimpleActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.cardview_data_student_viewgrade, parent,false);
        return new SimpleActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleActivityViewHolder holder, int position) {
        holder.setView(resultList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class SimpleActivityViewHolder extends RecyclerView.ViewHolder {
        private ImageView studentImage;
        private TextView studentDetail;
        private TextView editText;
        private Student student;
        private Spinner spinner;
        private LinearLayout layout;

        SimpleActivityViewHolder(View itemView) {
            super(itemView);
            studentImage = (ImageView) itemView.findViewById(R.id.f_data_student_profile);
            studentDetail = (TextView) itemView.findViewById(R.id.f_data_studentname_text);
            editText = (TextView) itemView.findViewById(R.id.f_data_student_viewgrade);
            layout = (LinearLayout) itemView.findViewById(R.id.bg_la);
        }

        void setView(final ActivityResult result, final int position) {
            if(result != null) {
                Student student = result.getStudent();
                String display = String.format(Locale.ENGLISH, "%s \t%s %s. %s - %d",
                        "1-A",
                        student.getFirstName(),
                        student.getMiddleName().substring(0, 1),
                        student.getLastName(),
                        student.getStudentNumber());
                studentDetail.setText(display);
                editText.setText(result.getScore() + "");
            }
        }
    }
}

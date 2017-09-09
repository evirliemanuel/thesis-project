package com.lieverandiver.thesisproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lieverandiver.thesisproject.R;
import com.remswork.project.alice.model.ExamResult;
import com.remswork.project.alice.model.ProjectResult;
import com.remswork.project.alice.model.Student;

import java.util.List;

public class ProjectResultAdapter extends RecyclerView
        .Adapter<ProjectResultAdapter.ProjectViewHolder> {

    private LayoutInflater layoutInflater;
    private List<ProjectResult> resultList;
    private Context context;
    private ProjectAdapter.OnClickListener onClickListener;

    public ProjectResultAdapter(Context context, List<ProjectResult> resultList) {
        layoutInflater = LayoutInflater.from(context);
        this.resultList = resultList;
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_z_result_project_cardview, parent,false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        holder.setView(resultList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    class ProjectViewHolder extends RecyclerView.ViewHolder {
        private TextView txName;
        private TextView txScore;
        private TextView txInit;


        ProjectViewHolder(View itemView) {
            super(itemView);
            txName = (TextView) itemView.findViewById(R.id.result_cardview_name5);
            txScore = (TextView) itemView.findViewById(R.id.result_cardview_score5);
            txInit = (TextView) itemView.findViewById(R.id.result_cardview_init5);
        }

        void setView(final ProjectResult result, final int position) {
            if(result != null) {
                Student student = result.getStudent();
                String name = String.format("%s %s. %s",
                        student.getFirstName(),
                        student.getMiddleName().substring(0, 1),
                        student.getLastName());
                String score = String.valueOf(result.getScore());
                String init = student.getFirstName().substring(0, 1);

                txName.setText(name);
                txScore.setText(score);
                txInit.setText(init);
            }
        }
    }
}
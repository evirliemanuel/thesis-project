package com.lieverandiver.thesisproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lieverandiver.thesisproject.R;
import com.remswork.project.alice.model.ProjectResult;
import com.remswork.project.alice.model.QuizResult;
import com.remswork.project.alice.model.Student;

import java.util.List;

public class QuizResultAdapter extends RecyclerView
        .Adapter<QuizResultAdapter.ProjectViewHolder> {

    private LayoutInflater layoutInflater;
    private List<QuizResult> resultList;
    private Context context;
    private ProjectAdapter.OnClickListener onClickListener;

    public QuizResultAdapter(Context context, List<QuizResult> resultList) {
        layoutInflater = LayoutInflater.from(context);
        this.resultList = resultList;
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_all_result_cardview, parent,false);
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
            txName = (TextView) itemView.findViewById(R.id.result_cardview_name);
            txScore = (TextView) itemView.findViewById(R.id.result_cardview_score);
            txInit = (TextView) itemView.findViewById(R.id.result_cardview_init);
        }

        void setView(final QuizResult result, final int position) {
            if(result != null) {
                Student student = result.getStudent();
                String name = String.format("%s, %s %s.",
                        student.getLastName(),
                        student.getFirstName(),
                        student.getMiddleName().substring(0, 1)
                );
                String score = String.valueOf(result.getScore());
                String init = student.getFirstName().substring(0, 1);

                txName.setText(name);
                txScore.setText(score);
                txInit.setText(init);
            }
        }
    }
}

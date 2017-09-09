package com.lieverandiver.thesisproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lieverandiver.thesisproject.R;
import com.remswork.project.alice.model.ActivityResult;
import com.remswork.project.alice.model.Assignment;
import com.remswork.project.alice.model.AssignmentResult;
import com.remswork.project.alice.model.Student;

import java.util.List;

public class AssignmentResultAdapter extends RecyclerView
        .Adapter<AssignmentResultAdapter.AssignmentViewHolder> {

    private LayoutInflater layoutInflater;
    private List<AssignmentResult> resultList;
    private Context context;
    private AssignmentAdapter.OnClickListener onClickListener;

    public AssignmentResultAdapter(Context context, List<AssignmentResult> resultList) {
        layoutInflater = LayoutInflater.from(context);
        this.resultList = resultList;
    }

    @Override
    public AssignmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_z_result_assignment_cardview, parent,false);
        return new AssignmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AssignmentViewHolder holder, int position) {
        holder.setView(resultList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    class AssignmentViewHolder extends RecyclerView.ViewHolder {
        private TextView txName;
        private TextView txScore;
        private TextView txInit;


        AssignmentViewHolder(View itemView) {
            super(itemView);
            txName = (TextView) itemView.findViewById(R.id.result_cardview_name2);
            txScore = (TextView) itemView.findViewById(R.id.result_cardview_score2);
            txInit = (TextView) itemView.findViewById(R.id.result_cardview_init2);
        }

        void setView(final AssignmentResult result, final int position) {
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

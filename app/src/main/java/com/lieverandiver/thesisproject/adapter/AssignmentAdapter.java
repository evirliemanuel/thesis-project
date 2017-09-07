package com.lieverandiver.thesisproject.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lieverandiver.thesisproject.R;
import com.remswork.project.alice.model.Activity;
import com.remswork.project.alice.model.Assignment;

import java.util.List;

/**
 * Created by Verlie on 9/6/2017.
 */

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentHolder> {

    private Context context;
    private AssignmentAdapter.OnClickListener onClickListener;
    private LayoutInflater layoutInflater;
    private List<Assignment> assignmentList;

    public AssignmentAdapter(Context context, List<Assignment> assignmentList) {
        layoutInflater = LayoutInflater.from(context);
        this.assignmentList = assignmentList;
        if(context instanceof AssignmentAdapter.OnClickListener)
            onClickListener = (AssignmentAdapter.OnClickListener) context;
    }

    @Override
    public AssignmentAdapter.AssignmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_cardview_record_assignment, parent, false);
      AssignmentHolder holder = new AssignmentHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AssignmentHolder holder, int position) {
        Assignment assignment = assignmentList.get(position);
        holder.setView(assignment, position);
    }

    @Override
    public int getItemCount() {
        return assignmentList.size();
    }

    public class AssignmentHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewDate;
        private TextView textViewTotal;
        private CardView cardView;

        AssignmentHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.assignment_nameview);
            textViewDate = (TextView) itemView.findViewById(R.id.assignment_dateview);
            textViewTotal = (TextView) itemView.findViewById(R.id.assignment_totalview);
            cardView = (CardView) itemView.findViewById(R.id.assignment_cardview);
        }

        public void setView(final Assignment assignment, int position) {

            String title = assignment.getTitle();
            String date = assignment.getDate();
            String total = String.valueOf(assignment.getItemTotal());

            textViewTitle.setText(title);
            textViewDate.setText(date);
            textViewTotal.setText(total);
            cardView.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View v) {
                    onClickListener.onClick(assignment, assignment.getId());
                }
            });
        }
    }

    public interface OnClickListener {
        void onClick(Assignment assignment, long assignmentId);
    }
}

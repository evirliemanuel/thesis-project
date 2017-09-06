package com.lieverandiver.thesisproject.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lieverandiver.thesisproject.R;
import com.remswork.project.alice.model.Activity;
import com.remswork.project.alice.model.Assignment;

import java.util.List;

/**
 * Created by Verlie on 9/6/2017.
 */

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentHolder> {

    private LayoutInflater layoutInflater;
    private List<Assignment> assignmentList;

    public AssignmentAdapter(Context context, List<Assignment> assignmentList) {
        layoutInflater = LayoutInflater.from(context);
        this.assignmentList = assignmentList;
    }

    @Override
    public AssignmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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

        private Assignment assignment;
        private TextView textViewTitle;
        private TextView textViewDate;
        private TextView textViewTotal;
        private CardView cardView;

        public AssignmentHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.assignment_name);
            textViewDate = (TextView) itemView.findViewById(R.id.assignment_date);
            cardView = (CardView) itemView.findViewById(R.id.assignment_cardview);

        }

        public void setView(Assignment assignment, int position) {
            this.assignment = assignment;
            textViewTitle.setText(assignment.getTitle());
            textViewDate.setText(assignment.getDate());

        }
    }
}

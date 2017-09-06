package com.lieverandiver.thesisproject.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lieverandiver.thesisproject.R;
import com.remswork.project.alice.model.Assignment;
import com.remswork.project.alice.model.Project;

import java.util.List;

/**
 * Created by Verlie on 9/6/2017.
 */
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectHolder> {

    private LayoutInflater layoutInflater;
    private List<Project> projectList;

    public ProjectAdapter(Context context, List<Project> projectList) {
        layoutInflater = LayoutInflater.from(context);
        this.projectList = projectList;
    }

    @Override
    public ProjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_cardview_record_project, parent, false);
        ProjectHolder holder = new ProjectHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProjectHolder holder, int position) {
        Project project = projectList.get(position);
        holder.setView(project, position);
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public class ProjectHolder extends RecyclerView.ViewHolder {

        private Project project;
        private TextView textViewTitle;
        private TextView textViewDate;
        private TextView textViewTotal;
        private CardView cardView;

        public ProjectHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.txtv_nameproject);
            textViewDate = (TextView) itemView.findViewById(R.id.txtv_dateproject);
            cardView = (CardView) itemView.findViewById(R.id.project_cardview);

        }

        public void setView(Project project, int position) {
            this.project = project;
            textViewTitle.setText(project.getTitle());
            textViewDate.setText(project.getDate());
            textViewTotal.setText(project.getItemTotal() + "");

        }
    }
}

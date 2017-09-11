package com.lieverandiver.thesisproject.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lieverandiver.thesisproject.R;
import com.remswork.project.alice.model.Project;

import java.util.List;

public class ProjectAdapterF extends RecyclerView.Adapter<ProjectAdapterF.ProjectViewHolder> {

    private Context context;
    private ProjectAdapterF.OnClickListener onClickListener;
    private LayoutInflater layoutInflater;
    private List<Project> projectList;

    public ProjectAdapterF(Context context, List<Project> projectList) {
        layoutInflater = LayoutInflater.from(context);
        this.projectList = projectList;
        if(context instanceof ProjectAdapterF.OnClickListener)
            onClickListener = (ProjectAdapterF.OnClickListener) context;
    }

    @Override
    public ProjectAdapterF.ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_z_add_project_cardview, parent, false);
        ProjectViewHolder holder = new ProjectViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        Project project = projectList.get(position);
        holder.setView(project, position);
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public class ProjectViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewDate;
        private EditText textViewTotal;
        private CardView cardView;

        ProjectViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.add_cardview_title5);
            textViewDate = (TextView) itemView.findViewById(R.id.add_cardview_date5);
            textViewTotal = (EditText) itemView.findViewById(R.id.add_cardview_total5);
            cardView = (CardView) itemView.findViewById(R.id.add_cardview_cardview5);
        }

        public void setView(final Project project, int position) {

            String title = project.getTitle();
            String date = project.getDate();
            String total = String.valueOf(project.getItemTotal());

            textViewTitle.setText(title);
            textViewDate.setText(date);
            textViewTotal.setText(total);
            cardView.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View v) {
                    onClickListener.onClick(project, project.getId());
                }
            });
        }
    }

    public interface OnClickListener {
        void onClick(Project project, long projectId);
    }
}

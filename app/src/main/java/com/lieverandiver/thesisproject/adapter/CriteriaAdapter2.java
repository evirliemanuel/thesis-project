package com.lieverandiver.thesisproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lieverandiver.thesisproject.R;

public class CriteriaAdapter2 extends RecyclerView.Adapter<CriteriaAdapter2.CriteriaViewHolder> {

    private LayoutInflater layoutInflater;
    private String[] formulas;

    public CriteriaAdapter2(Context context, String[] formulas) {
        layoutInflater = LayoutInflater.from(context);
        this.formulas = formulas;
    }

    @Override
    public CriteriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.criteria_layout_view, parent, false);
        return  new CriteriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CriteriaAdapter2.CriteriaViewHolder holder, int position) {
        String value = formulas[position];
        holder.setView(value, position);
    }

    @Override
    public int getItemCount() {
        return formulas.length;
    }

    public class CriteriaViewHolder extends RecyclerView.ViewHolder {

        private ImageView logo;
        private TextView title;
        private TextView percent;

        public CriteriaViewHolder(View itemView) {
            super(itemView);
            logo = (ImageView) itemView.findViewById(R.id.criteria_imageview_u1);
            title = (TextView) itemView.findViewById(R.id.criteria_name_u1);
            percent = (TextView) itemView.findViewById(R.id.criteria_per_u1);
        }

        public void setView(final String value, final int position) {
            if(position == 0) {
                logo.setImageResource(R.drawable.icon_activity);
                title.setText("Activity");
                percent.setText(value);
            }else if(position == 1) {
                logo.setImageResource(R.drawable.icon_assignment);
                title.setText("Assignment");
                percent.setText(value);
            }else if(position == 2) {
                logo.setImageResource(R.drawable.icon_attendance);
                title.setText("Attendance");
                percent.setText(value);
            }else if(position == 3) {
                logo.setImageResource(R.drawable.icon_exam);
                title.setText("Exam");
                percent.setText(value);
            }else if(position == 4) {
                logo.setImageResource(R.drawable.icon_project);
                title.setText("Project");
                percent.setText(value);
            }else {
                logo.setImageResource(R.drawable.icon_quiz);
                title.setText("Quiz");
                percent.setText(value);
            }
        }
    }
}

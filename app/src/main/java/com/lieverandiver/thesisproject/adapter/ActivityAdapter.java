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

import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Activity> activityList;
    private Context context;
    private OnClickListener onClickListener;

    public ActivityAdapter(Context context, List<Activity> activityList) {
        layoutInflater = LayoutInflater.from(context);
        this.activityList = activityList;
        if(context instanceof OnClickListener)
            onClickListener = (OnClickListener) context;
    }

    @Override
    public ActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_cardview_record_activity, parent, false);
        ActivityViewHolder holder = new ActivityViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ActivityViewHolder holder, int position) {
        Activity activity = activityList.get(position);
        holder.setView(activity, position);
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }

    public class ActivityViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewDate;
        private TextView textViewTotal;
        private CardView cardView;

        ActivityViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.f_gradecriteria_view_activity);
            textViewDate = (TextView) itemView.findViewById(R.id.f_grade_activity_date);
            textViewTotal = (TextView) itemView.findViewById(R.id.txtv_total1);
            cardView = (CardView) itemView.findViewById(R.id.fragment_slidebar_cardview_schedule);
        }

        public void setView(final Activity activity, int position) {

            String title = activity.getTitle();
            String date = activity.getDate();
            String total = String.valueOf(activity.getItemTotal());

            textViewTitle.setText(title);
            textViewDate.setText(date);
            textViewTotal.setText(total);
            cardView.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View v) {
                    onClickListener.onClick(activity, activity.getId());
                }
            });
        }
    }

    public interface OnClickListener {
        void onClick(Activity activity, long activityId);
    }
}

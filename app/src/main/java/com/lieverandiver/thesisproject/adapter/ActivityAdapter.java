package com.lieverandiver.thesisproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lieverandiver.thesisproject.R;
import com.remswork.project.alice.model.Activity;

import java.util.List;

/**
 * Created by Rem-sama on 9/3/2017.
 */

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Activity> activityList;

    public ActivityAdapter(Context context, List<Activity> activityList) {
        layoutInflater = LayoutInflater.from(context);
        this.activityList = activityList;
    }

    @Override
    public ActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_cardview_record_activity, parent, false);
        ActivityViewHolder holder = new ActivityViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(ActivityViewHolder holder, int position) {
        Activity activity = activityList.get(position);
        //holder.setView(activity, position);
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }

    public class ActivityViewHolder extends RecyclerView.ViewHolder {

        public ActivityViewHolder(View itemView) {
            super(itemView);
        }
    }
}

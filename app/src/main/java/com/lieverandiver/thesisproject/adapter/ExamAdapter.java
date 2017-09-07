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
import com.remswork.project.alice.model.Exam;

import java.util.List;

/**
 * Created by Verlie on 9/7/2017.
 */
public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Exam> examList;
    private Context context;
    private OnClickListener onClickListener;

    public ExamAdapter(Context context, List<Exam> examList) {
        layoutInflater = LayoutInflater.from(context);
        this.examList = examList;
        if(context instanceof OnClickListener)
            onClickListener = (OnClickListener) context;
    }

    @Override
    public ExamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_cardview_record_exam, parent, false);
        ExamViewHolder holder = new ExamViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ExamViewHolder holder, int position) {
        Exam exam = examList.get(position);
        holder.setView(exam, position);
    }

    @Override
    public int getItemCount() {
        return examList.size();
    }

    public class ExamViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewDate;
        private TextView textViewTotal;
        private CardView cardView;

        ExamViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.newid_examname);
            textViewDate = (TextView) itemView.findViewById(R.id.newid_examdate);
            textViewTotal = (TextView) itemView.findViewById(R.id.newid_examtotal);
            cardView = (CardView) itemView.findViewById(R.id.newid_cardviewexam);
        }

        public void setView(final Exam exam, int position) {

            String title = exam.getTitle();
            String date = exam.getDate();
            String total = String.valueOf(exam.getItemTotal());

            textViewTitle.setText(title);
            textViewDate.setText(date);
            textViewTotal.setText(total);
            cardView.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View v) {
                    onClickListener.onClick(exam, exam.getId());
                }
            });
        }
    }

    public interface OnClickListener {
        void onClick(Exam exam, long examId);
    }

}

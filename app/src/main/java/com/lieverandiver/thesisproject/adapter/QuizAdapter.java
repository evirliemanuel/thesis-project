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
import com.remswork.project.alice.model.Quiz;

import java.util.List;

/**
 * Created by Verlie on 9/6/2017.
 */

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    private Context context;
    private QuizAdapter.OnClickListener onClickListener;
    private LayoutInflater layoutInflater;
    private List<Quiz> quizList;

    public QuizAdapter(Context context, List<Quiz> quizList) {
        layoutInflater = LayoutInflater.from(context);
        this.quizList = quizList;
        if(context instanceof QuizAdapter.OnClickListener)
            onClickListener = (QuizAdapter.OnClickListener) context;
    }

    @Override
    public QuizAdapter.QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_z_add_quiz_cardview, parent, false);
        QuizViewHolder holder = new QuizViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(QuizViewHolder holder, int position) {
        Quiz quiz = quizList.get(position);
        holder.setView(quiz, position);
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewDate;
        private TextView textViewTotal;
        private CardView cardView;

        QuizViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.add_cardview_title6new6);
            textViewDate = (TextView) itemView.findViewById(R.id.add_cardview_date6new6);
            textViewTotal = (TextView) itemView.findViewById(R.id.add_cardview_total6new6);
            cardView = (CardView) itemView.findViewById(R.id.add_cardview_cardviewnew6);
        }

        public void setView(final Quiz quiz, int position) {

            String title = quiz.getTitle();
            String date = quiz.getDate();
            String total = String.valueOf(quiz.getItemTotal());

            textViewTitle.setText(title);
            textViewDate.setText(date);
            textViewTotal.setText(total);
            cardView.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View v) {
                    onClickListener.onClick(quiz, quiz.getId());
                }
            });
        }
    }

    public interface OnClickListener {
        void onClick(Quiz quiz, long quizId);
    }
}

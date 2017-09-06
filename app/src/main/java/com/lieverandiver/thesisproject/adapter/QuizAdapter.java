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
import com.remswork.project.alice.model.Quiz;

import java.util.List;

/**
 * Created by Verlie on 9/3/2017.
 */

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizHolder> {

    private LayoutInflater layoutInflater;
    private List<Quiz> quizList;

    public QuizAdapter(Context context, List<Quiz> quizList) {
        layoutInflater = LayoutInflater.from(context);
        this.quizList = quizList;
    }

    @Override
    public QuizHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_cardview_record_quiz, parent, false);
        QuizHolder holder = new QuizHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(QuizHolder holder, int position) {
        Quiz quiz = quizList.get(position);
        holder.setView(quiz, position);
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public class QuizHolder extends RecyclerView.ViewHolder {

        private Quiz quiz;
        private TextView textViewTitle;
        private TextView textViewDate;
        private TextView textViewTotal;
        private CardView cardView;

        public QuizHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.txtv_namequiz);
            textViewDate = (TextView) itemView.findViewById(R.id.txtv_datequiz);
            cardView = (CardView) itemView.findViewById(R.id.cardview_quiz);

        }

        public void setView(Quiz quiz, int position) {
            this.quiz = quiz;
            textViewTitle.setText(quiz.getTitle());
            textViewDate.setText(quiz.getDate());

        }
    }
}

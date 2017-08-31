package com.lieverandiver.thesisproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lieverandiver.thesisproject.R;
import com.remswork.project.alice.model.Class;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassAdapterViewHolder> {

    private List<Class> classList;
    private Context context;
    private LayoutInflater layoutInflater;

    public ClassAdapter(Context context, List<Class> classList) {
        this.context = context;
        this.classList = classList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ClassAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.cardview_data_clazz, parent, false);
        return new ClassAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClassAdapterViewHolder holder, int position) {
        holder.setView(classList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }

     public class ClassAdapterViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textViewSubject;
        private TextView textViewSimpleText;

        ClassAdapterViewHolder(View itemView) {
            super(itemView);
            textViewSubject = (TextView) itemView.findViewById(
                    R.id.fragment_slidebar_cardview_clazz_text_subject);
            textViewSimpleText = (TextView) itemView.findViewById(
                    R.id.fragment_slidebar_cardview_clazz_text_section);
        }

        void setView(final Class _class, final int position) {
            if(_class.getSubject() != null)
                textViewSubject.setText(_class.getSubject().getName());
        }
    }


}

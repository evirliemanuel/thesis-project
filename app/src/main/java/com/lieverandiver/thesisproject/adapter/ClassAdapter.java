package com.lieverandiver.thesisproject.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lieverandiver.thesisproject.R;
import com.remswork.project.alice.model.Class;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassAdapterViewHolder> {

    private List<Class> classList;
    private Context context;
    private LayoutInflater layoutInflater;
    private ClassAdapterListener classAdapterListener;

    public ClassAdapter(Context context, List<Class> classList) {
        this.context = context;
        this.classList = classList;
        layoutInflater = LayoutInflater.from(context);
        if(context instanceof ClassAdapterListener)
            classAdapterListener = (ClassAdapterListener) context;
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
         private ImageView buttonClick;

        ClassAdapterViewHolder(View itemView) {
            super(itemView);
            textViewSubject = (TextView) itemView.findViewById(
                    R.id.fragment_slidebar_cardview_clazz_text_subject);
            textViewSimpleText = (TextView) itemView.findViewById(
                    R.id.fragment_slidebar_cardview_clazz_text_section);
            buttonClick = (ImageView) itemView.findViewById(R.id.button_click);
        }

        void setView(final Class _class, final int position) {
            if(_class.getSubject() != null)
                textViewSubject.setText(_class.getSubject().getName());
            buttonClick.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("myTAG", classAdapterListener + "");
                    classAdapterListener.showClassView(_class.getId());
                }
            });
        }
    }

    public interface ClassAdapterListener {
        void showClassView(long id);
    }

}

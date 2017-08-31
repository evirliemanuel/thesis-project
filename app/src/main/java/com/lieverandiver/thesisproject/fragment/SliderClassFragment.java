package com.lieverandiver.thesisproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lieverandiver.thesisproject.R;
import com.lieverandiver.thesisproject.adapter.ClassAdapter;
import com.remswork.project.alice.exception.ClassException;
import com.remswork.project.alice.model.Class;
import com.remswork.project.alice.service.impl.ClassServiceImpl;

import java.util.List;

public class SliderClassFragment extends Fragment {

    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_slidebar_class, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.class_recyclerview);
        init();
        return view;
    }

    public void init() {
        try {
            List<Class> classList = new ClassServiceImpl().getClassList();
            ClassAdapter classAdapter = new ClassAdapter(getContext(), classList);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);

            recyclerView.setAdapter(classAdapter);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        } catch (ClassException e) {
            e.printStackTrace();
        }
    }
}

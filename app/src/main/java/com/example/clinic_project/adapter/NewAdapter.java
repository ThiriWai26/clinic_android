package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.holder.NewHolder;

public class NewAdapter extends RecyclerView.Adapter<NewHolder> {

    NewHolder.OnItemClickListener listener;

    public NewAdapter(NewHolder.OnItemClickListener listener){

        this.listener = listener;
    }

    @NonNull
    @Override
    public NewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return NewHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewHolder newHolder, int i) {

        NewHolder.bindData();

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}

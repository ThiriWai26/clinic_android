package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.holder.DragHolder;
import com.example.clinic_project.holder.ExaminationHolder;

public class DragAdapter extends RecyclerView.Adapter<DragHolder> {

    DragHolder.OnItemClickListener listener;

    public DragAdapter(DragHolder.OnItemClickListener listener) {

        this.listener = listener;
    }

    public DragHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return DragHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull DragHolder dragHolder, int i) {

        DragHolder.bindData();
    }



    public int getItemCount() {

        return 5;
    }


}

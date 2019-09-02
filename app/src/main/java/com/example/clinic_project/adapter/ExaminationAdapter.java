package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.holder.ExaminationHolder;

public class ExaminationAdapter extends RecyclerView.Adapter<ExaminationHolder> {

    ExaminationHolder.OnItemClickListener listener;

    public ExaminationAdapter(ExaminationHolder.OnItemClickListener listener) {

        this.listener = listener;
    }

    public ExaminationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return ExaminationHolder.create(inflater,viewGroup,listener);
    }

    public void onBindViewHolder(@NonNull ExaminationHolder examinationHolder, int i) {

        ExaminationHolder.bindData();

    }

    public int getItemCount() {

        return 5;
    }


}

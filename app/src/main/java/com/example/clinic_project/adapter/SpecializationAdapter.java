package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.holder.SpecializationHolder;
import com.example.clinic_project.model.SpecializationList;

import java.util.ArrayList;
import java.util.List;

public class SpecializationAdapter extends RecyclerView.Adapter<SpecializationHolder> {

    private SpecializationHolder.OnHolderItemClickListener listener;
    private List<SpecializationList> lists=new ArrayList<>();

    public SpecializationAdapter(SpecializationHolder.OnHolderItemClickListener listener){
        this.listener=listener;
    }
    @NonNull
    @Override
    public SpecializationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        return SpecializationHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecializationHolder specializationHolder, int i) {

        specializationHolder.bindData(lists.get(i));

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void addData( List<SpecializationList> lists){
        this.lists=lists;
        notifyDataSetChanged();
    }
}

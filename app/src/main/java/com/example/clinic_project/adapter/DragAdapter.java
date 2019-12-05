package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.holder.DragHolder;
import com.example.clinic_project.holder.ExaminationHolder;
import com.example.clinic_project.model.Service;

import java.util.ArrayList;
import java.util.List;

public class DragAdapter extends RecyclerView.Adapter<DragHolder> {

    List<Service> services;
    DragHolder.OnItemClickListener listener;

    public DragAdapter(DragHolder.OnItemClickListener listener) {
        services = new ArrayList<>();
        this.listener = listener;
    }

    public void addItem(List<Service> serviceList) {
        this.services.clear();
        this.services.addAll(serviceList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DragHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return DragHolder.create(inflater, viewGroup, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull DragHolder dragHolder, int i) {
        dragHolder.bindData(services.get(i));

    }

    @Override
    public int getItemCount() {
        return services.size();
    }
}

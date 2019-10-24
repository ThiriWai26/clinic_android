package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.holder.ServiceByLabHolder;

public class ServiceByLabAdapter extends RecyclerView.Adapter<ServiceByLabHolder> {

    ServiceByLabHolder.OnItemClickListener listener;

    public ServiceByLabAdapter(ServiceByLabHolder.OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ServiceByLabHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return ServiceByLabHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceByLabHolder serviceByLabHolder, int i) {

        ServiceByLabHolder.bindData();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

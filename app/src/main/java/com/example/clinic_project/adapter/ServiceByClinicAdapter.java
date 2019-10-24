package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.holder.ServiceByClinicHolder;

public class ServiceByClinicAdapter extends RecyclerView.Adapter<ServiceByClinicHolder> {

    ServiceByClinicHolder.OnItemClickListener listener;

    public ServiceByClinicAdapter(ServiceByClinicHolder.OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ServiceByClinicHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return ServiceByClinicHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceByClinicHolder serviceByClinicHolder, int i) {
        ServiceByClinicHolder.binData();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

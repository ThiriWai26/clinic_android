package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.holder.ServiceHolder;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceHolder> {

    ServiceHolder.OnItemClickListener listener;

    public ServiceAdapter(ServiceHolder.OnItemClickListener listener){

        this.listener = listener;
    }

    @NonNull
    @Override
    public ServiceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return ServiceHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceHolder serviceHolder, int i) {

        ServiceHolder.bindData();

    }

    @Override
    public int getItemCount() {

        return 5;
    }
}

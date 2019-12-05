package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.holder.RegistrationHolder;
import com.example.clinic_project.model.Service;

import java.util.ArrayList;
import java.util.List;

public class RegistrationAdapter extends RecyclerView.Adapter<RegistrationHolder> {

    List<Service> services;
    RegistrationHolder.OnItemClickListener listener;

    public RegistrationAdapter(RegistrationHolder.OnItemClickListener listener) {
        services = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public RegistrationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return RegistrationHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RegistrationHolder registrationHolder, int i) {

        registrationHolder.bindData(services.get(i));
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public void addItem(List<Service> service) {
        this.services.clear();
        this.services.addAll(service);
        notifyDataSetChanged();
    }
}

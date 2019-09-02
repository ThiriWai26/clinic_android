package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.holder.RegistrationHolder;

public class RegistrationAdapter extends RecyclerView.Adapter<RegistrationHolder> {

    RegistrationHolder.OnItemClickListener listener;

    public RegistrationAdapter(RegistrationHolder.OnItemClickListener listener) {

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

        RegistrationHolder.bindData();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

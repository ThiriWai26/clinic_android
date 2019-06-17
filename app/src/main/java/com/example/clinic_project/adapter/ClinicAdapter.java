package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.Activity.ClinicActivity;
import com.example.clinic_project.holder.ClinicHolder;
import com.example.clinic_project.model.Clinic;

import java.util.ArrayList;
import java.util.List;

public class ClinicAdapter extends RecyclerView.Adapter<ClinicHolder> {

    List<Clinic> clinics;
    ClinicHolder.OnClinicClickListener listener;

    public ClinicAdapter (ClinicActivity listener){

        clinics=new ArrayList<>();
        this.listener=listener;
    }

    @NonNull
    @Override
    public ClinicHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        return ClinicHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClinicHolder clinicHolder, int i) {

        clinicHolder.bindData(clinics.get(i));
    }

    @Override
    public int getItemCount() {
        return clinics.size();
    }


    public void addItem(List<Clinic> clinic) {

        this.clinics.clear();
        this.clinics.addAll(clinic);
        notifyDataSetChanged();
    }
}

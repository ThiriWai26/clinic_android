package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.holder.ClinicHolder;
import com.example.clinic_project.model.Building;

import java.util.List;

public class ClinicAdapter extends RecyclerView.Adapter<ClinicHolder> {

    List<Building> buildings;
    ClinicHolder.OnClinicClickListener listener;

    @NonNull
    @Override
    public ClinicHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater= LayoutInflater.from(viewGroup.getContext());
        return ClinicHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClinicHolder clinicHolder, int i) {
        ClinicHolder.bindData(buildings.get(i));
    }

    @Override
    public int getItemCount() {
        return buildings.size();
    }

    public void addItem(List<Building> building) {

        this.buildings.clear();
        this.buildings.addAll(buildings);
        notifyDataSetChanged();
    }
}

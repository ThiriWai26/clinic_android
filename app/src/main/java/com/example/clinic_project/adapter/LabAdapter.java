package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.holder.LabHolder;
import com.example.clinic_project.model.Building;

import java.util.ArrayList;
import java.util.List;

public class LabAdapter extends RecyclerView.Adapter<LabHolder> {

    List<Building> buildings;
    LabHolder.OnLabClickListener listener;

    public LabAdapter(LabHolder.OnLabClickListener listener) {

        buildings = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public LabHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater= LayoutInflater.from(viewGroup.getContext());
        return LabHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull LabHolder labHolder, int i) {

        LabHolder.bindData(buildings.get(i));
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

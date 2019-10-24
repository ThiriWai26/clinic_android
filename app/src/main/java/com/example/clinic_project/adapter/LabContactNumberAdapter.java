package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.Activity.LabDetailActivity;
import com.example.clinic_project.holder.LabContactNumberHolder;
import com.example.clinic_project.model.BuildingDetail;

import java.util.ArrayList;
import java.util.List;

public class LabContactNumberAdapter extends RecyclerView.Adapter<LabContactNumberHolder> {

    List<BuildingDetail> buildingDetailList;
    LabContactNumberHolder.OnItemClickListener listener;

    public LabContactNumberAdapter(LabContactNumberHolder.OnItemClickListener listener) {

        buildingDetailList = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public LabContactNumberHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return LabContactNumberHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull LabContactNumberHolder labContactNumberHolder, int i) {
        LabContactNumberHolder.bindData();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

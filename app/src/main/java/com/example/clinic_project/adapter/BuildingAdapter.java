package com.example.clinic_project.adapter;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.holder.BuildingHolder;
import com.example.clinic_project.model.Building;

import java.util.ArrayList;
import java.util.List;

public class BuildingAdapter extends RecyclerView.Adapter<BuildingHolder> {

    List<Building> buildings;
    BuildingHolder.OnBuildingClickListener listener;

    public BuildingAdapter(BuildingHolder.OnBuildingClickListener listener){

        buildings=new ArrayList<>();
        this.listener=listener;
    }

    @NonNull
    @Override
    public BuildingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        return BuildingHolder.create(inflater,viewGroup,listener);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(@NonNull BuildingHolder buildingHolder, int i) {

        buildingHolder.bindData(buildings.get(i));
    }

    @Override
    public int getItemCount() {
        return buildings.size();
    }

    public void addItem(List<Building> buildings){

        this.buildings.clear();
        this.buildings.addAll(buildings);
        notifyDataSetChanged();

    }
}
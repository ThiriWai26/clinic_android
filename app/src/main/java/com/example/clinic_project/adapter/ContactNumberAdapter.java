package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.Activity.HospitalDetailActivity;
import com.example.clinic_project.holder.ContactNumberHolder;
import com.example.clinic_project.holder.DragHolder;
import com.example.clinic_project.holder.ExaminationHolder;
import com.example.clinic_project.model.BuildingDetail;

import java.util.ArrayList;
import java.util.List;

public class ContactNumberAdapter extends RecyclerView.Adapter<ContactNumberHolder> {

    List<BuildingDetail> buildingDetailList;
    ContactNumberHolder.OnItemClickListener listener;

    public ContactNumberAdapter(HospitalDetailActivity listener) {

        buildingDetailList = new ArrayList<>();
        this.listener = (ContactNumberHolder.OnItemClickListener) listener;
    }

    public ContactNumberHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return ContactNumberHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactNumberHolder contactNumberHolder, int i) {

        ContactNumberHolder.bindData();
    }

    public int getItemCount() {

        return 5;
    }

//    public void addItem(List<BuildingDetail> buildingDetails){
//
//        this.buildingDetailList.clear();
//        this.buildingDetailList.addAll(buildingDetails);
//        notifyDataSetChanged();
//
//    }

}

package com.example.clinic_project.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clinic_project.holder.HospitalScheduleHolder;
import com.example.clinic_project.model.HospitalSchedule;

import java.util.ArrayList;
import java.util.List;

public class HospitalScheduleAdapter extends RecyclerView.Adapter<HospitalScheduleHolder>  {

    List<HospitalSchedule> hospitalSchedules=new ArrayList<>();
    HospitalScheduleHolder.OnItemClickListener listener;

    public HospitalScheduleAdapter(HospitalScheduleHolder.OnItemClickListener listener){

        this.listener=listener;

    }

    @NonNull
    @Override
    public HospitalScheduleHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        return HospitalScheduleHolder.create(inflater,viewGroup,listener);
    }


    @Override
    public void onBindViewHolder(@NonNull HospitalScheduleHolder bookHolder, final int i) {

        bookHolder.bindData(hospitalSchedules.get(i));


//        if(selectedPosition == i)
//            bookHolder.itemView.setBackgroundColor(Color.parseColor("#8FFCCF"));
//
//        else
//            bookHolder.itemView.setBackgroundColor(Color.WHITE);
//
//        bookHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedPosition = i;
//                notifyDataSetChanged();
//            }
//        });

    }

    @Override
    public int getItemCount() {

        Log.e("Size",String.valueOf(hospitalSchedules.size()));
        return hospitalSchedules.size();

    }

    public void addItem(List<HospitalSchedule> hospitalSchedules){

        this.hospitalSchedules.clear();
        this.hospitalSchedules.addAll(hospitalSchedules);
        notifyDataSetChanged();
        Log.e("add Item","Success");

    }

    public void clearItem(){
        this.hospitalSchedules.clear();
        notifyDataSetChanged();
    }
}

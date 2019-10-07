package com.example.clinic_project.holder;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic_project.R;
import com.example.clinic_project.model.HospitalSchedule;

public class HospitalScheduleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private RelativeLayout bookItem;
    private OnItemClickListener listener;
    private TextView starttime, endtime, booking;


    public HospitalScheduleHolder(@NonNull View itemView, OnItemClickListener listener) {

        super(itemView);
        this.listener=listener;

        initItemView();
    }

    private void initItemView() {

        starttime = itemView.findViewById(R.id.tvstarttime);
        endtime = itemView.findViewById(R.id.tvendtime);
        bookItem = itemView.findViewById(R.id.bookItem);
        booking = itemView.findViewById(R.id.tvbooking);

        itemView.setOnClickListener(this);

    }

    public void bindData(final HospitalSchedule hospitalSchedule) {

        starttime.setText(hospitalSchedule.startTime);
        endtime.setText(hospitalSchedule.endTime);

        Log.e("startTime",hospitalSchedule.startTime);
        Log.e("endTime",hospitalSchedule.endTime);

        bookItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("bookItemClick","success");
            }
        });


    }

    @Override
    public void onClick(View v) {

        listener.onItemClick(String.valueOf(v),getAdapterPosition());
        int position;
        position = getAdapterPosition();
        Log.e("position", String.valueOf(position));

    }

    public interface OnItemClickListener  {
        void onItemClick(String valueOf, int adapterPosition);
    }

    public static HospitalScheduleHolder create(LayoutInflater inflater, ViewGroup viewGroup, OnItemClickListener listener) {
        View view = inflater.inflate(R.layout.hospital_schedule, viewGroup, false);
        Log.e("holder","Success");
        return new HospitalScheduleHolder(view,listener);
    }


}

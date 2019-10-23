package com.example.clinic_project.holder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic_project.Activity.CalenderViewActivity;
import com.example.clinic_project.Activity.ClinicDetailActivity;
import com.example.clinic_project.Activity.HospitalDetailActivity;
import com.example.clinic_project.Activity.RegisterActivity;
import com.example.clinic_project.R;
import com.example.clinic_project.model.HospitalSchedule;

public class HospitalScheduleHolder extends RecyclerView.ViewHolder {

    private RelativeLayout bookItem;
    private OnItemClickListener listener;
    private TextView id,starttime, endtime, booking;
    private ImageView imgmark;
    private boolean isMark;

    public HospitalScheduleHolder(@NonNull View itemView, OnItemClickListener listener) {

        super(itemView);
        this.listener=listener;

        initItemView();
    }

    private void initItemView() {

        id = itemView.findViewById(R.id.txid);
        starttime = itemView.findViewById(R.id.tvstarttime);
        endtime = itemView.findViewById(R.id.tvendtime);
        bookItem = itemView.findViewById(R.id.bookItem);
        booking = itemView.findViewById(R.id.tvbooking);
        imgmark = itemView.findViewById(R.id.imgmark);

    }

    public void bindData(final HospitalSchedule hospitalSchedule) {

        starttime.setText(hospitalSchedule.startTime);
        endtime.setText(hospitalSchedule.endTime);

        Log.e("schedule_id", String.valueOf(hospitalSchedule.id));
        Log.e("startTime",hospitalSchedule.startTime);
        Log.e("endTime",hospitalSchedule.endTime);

        bookItem.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                listener.onItemClick(hospitalSchedule.id);

                if(isMark) {
                    Log.e("Mark","false");
                    imgmark.setBackgroundResource(0);
                    isMark=false;

                }
                else{
                    Log.e("Mark","true");
                    imgmark.setBackgroundResource(R.drawable.correctmark);
                    isMark=true;
                }
            }
        });
    }


    public interface OnItemClickListener  {
        void onItemClick(int scheduleId);
    }

    public static HospitalScheduleHolder create(LayoutInflater inflater, ViewGroup viewGroup, OnItemClickListener listener) {
        View view = inflater.inflate(R.layout.hospital_schedule, viewGroup, false);
        Log.e("holder","Success");
        return new HospitalScheduleHolder(view,listener);
    }


}

package com.example.clinic_project.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.clinic_project.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyBookingHolder extends RecyclerView.ViewHolder {

    private OnItemClickListener listener;
    private CircleImageView imgdoctor;
    private TextView txtdoctorname,txthospitalname,txtDate,txtMap;
    private Button btncancel;

    public MyBookingHolder(@NonNull View itemView, OnItemClickListener listener) {

        super(itemView);
        this.listener = listener;
        txtdoctorname = itemView.findViewById(R.id.tvName);
        txthospitalname= itemView.findViewById(R.id.tvhospitalname);
        txtDate = itemView.findViewById(R.id.tvtime);
        txtMap = itemView.findViewById(R.id.tvMap);

    }

    public static void bindData() {

    }

    public static MyBookingHolder create(LayoutInflater inflater, ViewGroup viewGroup, OnItemClickListener listener) {

        View view = inflater.inflate(R.layout.layout_booking_list, viewGroup, false);
        Log.e("holder","Success");
        return new MyBookingHolder(view,listener);
    }

    public interface OnItemClickListener  {

        void onItemClick(String date, int timeId);

    }
}



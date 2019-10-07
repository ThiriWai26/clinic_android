package com.example.clinic_project.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.model.Bookings;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyBookingHolder extends RecyclerView.ViewHolder   {

    private OnItemClickListener listener;

    private static ImageView imgdoctor;
    private static TextView txtdoctorname;
    private static TextView txthospitalname;
    private static TextView txtDate;
    private static TextView txttimetable;
    private TextView txtMap;
    private static Button btncancel;

    public static MyBookingHolder create(LayoutInflater inflater, ViewGroup viewGroup, OnItemClickListener listener) {

        View view = inflater.inflate(R.layout.layout_booking_list, viewGroup, false);
        Log.e("holder","Success");
        return new MyBookingHolder(view,listener);

    }

    public static void bindData(Bookings bookings) {

        String doctorname = String.valueOf(bookings.doctor.doctorName);
        txtdoctorname.setText(doctorname);

        String hospitalname = String.valueOf(bookings.hospital.hospitalName);
        txthospitalname.setText(hospitalname);

        txtDate.setText(bookings.date);
        Picasso.get()
                .load("http://192.168.100.201:8001/api/download_image/" + bookings.doctor.photo)
                .resize(40, 40)
                .onlyScaleDown()
                .centerCrop()
                .into(imgdoctor);

        Log.e("doctor name",doctorname);
        Log.e("hospital name",hospitalname);
        Log.e("date",bookings.date);
        Log.e("photo", String.valueOf(imgdoctor));

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("cancel","ok");
            }
        });
    }


    public MyBookingHolder(@NonNull View itemView, OnItemClickListener listener) {

        super(itemView);
        this.listener = listener;
        initView(itemView);

    }

    private void initView(View itemView) {

        imgdoctor = itemView.findViewById(R.id.profile);
        txtdoctorname = itemView.findViewById(R.id.tvName);
        txthospitalname= itemView.findViewById(R.id.tvhospitalname);
        txtDate = itemView.findViewById(R.id.tvtime);
        txtMap = itemView.findViewById(R.id.tvMap);
        btncancel = itemView.findViewById(R.id.btncancel);

    }

    public interface OnItemClickListener  {

        void onItemClick(int parseInt);
    }

}



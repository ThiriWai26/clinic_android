package com.example.clinic_project.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.clinic_project.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyFavouriteDoctorHolder extends RecyclerView.ViewHolder {

    private OnItemClickListener listener;
    private CircleImageView imgview;
    private TextView txtname,txttype;

    public MyFavouriteDoctorHolder(@NonNull View itemView, OnItemClickListener listener) {
        super(itemView);
        this.listener = listener;
        imgview = itemView.findViewById(R.id.profile);
        txtname = itemView.findViewById(R.id.tvName);
        txttype = itemView.findViewById(R.id.tvType);
    }

    public static void bindData() {
    }


    public static MyFavouriteDoctorHolder create(LayoutInflater inflater, ViewGroup viewGroup, OnItemClickListener listener) {

        View view = inflater.inflate(R.layout.layout_favdoctor_list, viewGroup , false);
        Log.e("holder","success");
        return new MyFavouriteDoctorHolder(view,listener);
    }

    public interface OnItemClickListener {

    }
}

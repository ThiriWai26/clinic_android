package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.holder.MyFavouriteDoctorHolder;

public class MyFavouriteDoctorAdapter extends RecyclerView.Adapter<MyFavouriteDoctorHolder> {

    MyFavouriteDoctorHolder.OnItemClickListener listener;

    public MyFavouriteDoctorAdapter(MyFavouriteDoctorHolder.OnItemClickListener listener){

        this.listener = listener;
    }


    @NonNull
    @Override
    public MyFavouriteDoctorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return MyFavouriteDoctorHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFavouriteDoctorHolder myFavouriteDoctorHolder, int i) {

        MyFavouriteDoctorHolder.bindData();

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}

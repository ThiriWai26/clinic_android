package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.holder.MyFavouriteHospitalHolder;

public class MyFavouriteHospitalAdapter extends RecyclerView.Adapter<MyFavouriteHospitalHolder> {

    MyFavouriteHospitalHolder.OnItemClickListener listener;

    public MyFavouriteHospitalAdapter (MyFavouriteHospitalHolder.OnItemClickListener listener){

        this.listener = listener;
    }


    @NonNull
    @Override
    public MyFavouriteHospitalHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return  MyFavouriteHospitalHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFavouriteHospitalHolder myFavouriteHospitalHolder, int i) {

        MyFavouriteHospitalHolder.bindData();

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}

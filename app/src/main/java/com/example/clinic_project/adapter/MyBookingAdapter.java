package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.holder.MyBookingHolder;

public class MyBookingAdapter extends RecyclerView.Adapter<MyBookingHolder> {

    MyBookingHolder.OnItemClickListener listener;

    public MyBookingAdapter (MyBookingHolder.OnItemClickListener listener){

        this.listener = listener;
    }

    @NonNull
    @Override
    public MyBookingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return MyBookingHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBookingHolder myBookingHolder, int i) {

        MyBookingHolder.bindData();
    }

    @Override
    public int getItemCount() {

        return 10;
    }
}






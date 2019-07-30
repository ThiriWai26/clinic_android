package com.example.clinic_project.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.clinic_project.Activity.MyBookingActivity;
import com.example.clinic_project.holder.BookHolder;
import com.example.clinic_project.model.Booking;

import java.util.ArrayList;
import java.util.List;


public class BookAdapter extends RecyclerView.Adapter<BookHolder> {

    List<Booking> bookings=new ArrayList<>();
    BookHolder.OnItemClickListener listener;
    int selectedPosition = -1;

    public BookAdapter(BookHolder.OnItemClickListener listener){

        this.listener=listener;

    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        return BookHolder.create(inflater,viewGroup,listener);
    }


    @Override
    public void onBindViewHolder(@NonNull BookHolder bookHolder, final int i) {

        bookHolder.bindData(bookings.get(i));


        //highlight color in selected item
//        if(selectedPosition == i)
//            bookHolder.itemView.setBackgroundColor(Color.parseColor("#e2a49e"));
//        else
//            bookHolder.itemView.setBackgroundColor(Color.parseColor("#f5f5f5"));
//
//        bookHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedPosition = i;
//                notifyDataSetChanged();
//            }
//        });

        if(selectedPosition == i)
            bookHolder.itemView.setBackgroundColor(Color.parseColor("#8FFCCF"));

        else
            bookHolder.itemView.setBackgroundColor(Color.WHITE);

        bookHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = i;
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {

        Log.e("Size",String.valueOf(bookings.size()));
        return bookings.size();

    }

    public void addItem(List<Booking> bookings){

        this.bookings.clear();
        this.bookings.addAll(bookings);
        notifyDataSetChanged();
        Log.e("add Item","Success");

    }
}

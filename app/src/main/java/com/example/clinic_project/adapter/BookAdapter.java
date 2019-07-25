package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.holder.BookHolder;
import com.example.clinic_project.model.Booking;

import java.util.ArrayList;
import java.util.List;


public class BookAdapter extends RecyclerView.Adapter<BookHolder> {

    List<Booking> bookings=new ArrayList<>();
    BookHolder.OnItemClickListener listener;

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
    public void onBindViewHolder(@NonNull BookHolder bookHolder, int i) {

        bookHolder.bindData(bookings.get(i));

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

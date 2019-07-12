package com.example.clinic_project.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.model.Booking;

public class BookHolder extends RecyclerView.ViewHolder  {

    private OnItemClickListener listener;
    private TextView name,location,time;

    public void bindData() {

        time.setText("Time");

    }

    public interface OnItemClickListener {
         void onItemClick(String date, int timeId);

    }

    public BookHolder(@NonNull View itemView, OnItemClickListener listener) {

        super(itemView);
        this.listener=listener;
        name = itemView.findViewById(R.id.tvname);
        time = itemView.findViewById(R.id.tvTime);
        location = itemView.findViewById(R.id.tvLocation);

    }


    public static BookHolder create(LayoutInflater inflater, ViewGroup parent,OnItemClickListener listener) {

        View view = inflater.inflate(R.layout.book_item, parent, false);
        Log.e("holder","Success");
        return new BookHolder(view,listener);

    }


}

package com.example.clinic_project.holder;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic_project.R;
import com.example.clinic_project.model.Booking;

public class BookHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private RelativeLayout bookItem;
    private OnItemClickListener listener;
    private TextView BuildingName, time;

    public void bindData(final Booking booking) {

        BuildingName.setText(booking.clinicName);
        time.setText(booking.startTime);

        bookItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("bookingstatus","ok");
                if(booking.bookStatus==0){
                    Toast.makeText(v.getContext(),"This cannot be booked",Toast.LENGTH_LONG).show();
                }
                else if(booking.bookStatus==2){
                    listener.onItemClick(booking.date, booking.timeId);
                    bookItem.setBackgroundColor(Color.parseColor("#f0f0f0"));
                }
                else if(booking.bookStatus==1){
                    Toast.makeText(v.getContext(),"Already Booked",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

        listener.onItemClick(String.valueOf(v),getAdapterPosition());



    }

    public interface OnItemClickListener  {
         void onItemClick(String date, int timeId);

    }

    public BookHolder(@NonNull View itemView, OnItemClickListener listener) {

        super(itemView);
        this.listener=listener;
        BuildingName = itemView.findViewById(R.id.tvname);
        time = itemView.findViewById(R.id.tvTime);
        bookItem = itemView.findViewById(R.id.bookItem);

        itemView.setOnClickListener(this);

    }

    public static BookHolder create(LayoutInflater inflater, ViewGroup viewGroup, OnItemClickListener listener) {
        View view = inflater.inflate(R.layout.book_item, viewGroup, false);
        Log.e("holder","Success");
        return new BookHolder(view,listener);
    }


}

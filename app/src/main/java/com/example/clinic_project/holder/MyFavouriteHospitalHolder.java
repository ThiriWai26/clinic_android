package com.example.clinic_project.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.R;

public class MyFavouriteHospitalHolder extends RecyclerView.ViewHolder {

    private TextView txthospital;
    private ImageView imgsetting;
    private OnItemClickListener listener;

    public MyFavouriteHospitalHolder(@NonNull View itemView, OnItemClickListener listener) {
        super(itemView);
        this.listener = listener;
        txthospital = itemView.findViewById(R.id.txthospital);
        imgsetting = itemView.findViewById(R.id.imgsetting);
    }

    public static void bindData() {
    }

    public static MyFavouriteHospitalHolder create(LayoutInflater inflater, ViewGroup viewGroup, OnItemClickListener listener) {

        View view = inflater.inflate(R.layout.layout_favhospital_layout, viewGroup , false);
        Log.e("holder","success");
        return new MyFavouriteHospitalHolder(view,listener);
    }

    public interface OnItemClickListener{

    }
}

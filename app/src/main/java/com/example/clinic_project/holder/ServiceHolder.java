package com.example.clinic_project.holder;

import android.media.Image;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.R;

public class ServiceHolder extends RecyclerView.ViewHolder  {

    private OnItemClickListener listener;
    private TextView texttitle,textservice,textprice,btnbook,btnsave;
    private ImageView imgView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ServiceHolder(@NonNull View itemView, OnItemClickListener listener) {

        super(itemView);
        this.listener = listener;
        btnbook = itemView.findViewById(R.id.btnbook);
//        btnsave = itemView.findViewById(R.id.btnsave);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ServiceHolder create(LayoutInflater inflater, ViewGroup parent, ServiceHolder.OnItemClickListener listener) {

        View view = inflater.inflate(R.layout.layout_service_cardview, parent, false);
        return new ServiceHolder(view, listener);
    }

    public static void bindData() {
    }

    public interface OnItemClickListener  {


    }


}

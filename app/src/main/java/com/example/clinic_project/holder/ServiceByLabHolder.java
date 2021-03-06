package com.example.clinic_project.holder;

import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.R;

public class ServiceByLabHolder extends RecyclerView.ViewHolder {

    private OnItemClickListener listener;
    private TextView name, about;
    private ImageView featurephoto, book, save;

    public ServiceByLabHolder(@NonNull View itemView, OnItemClickListener listener) {
        super(itemView);
        this.listener = this.listener;

        name = itemView.findViewById(R.id.tvname);
        about = itemView.findViewById(R.id.tvabout);
        featurephoto = itemView.findViewById(R.id.featuredphoto);
        book = itemView.findViewById(R.id.imagebook);
        save = itemView.findViewById(R.id.imagefav);
    }

    public static void bindData() {
    }

    public static ServiceByLabHolder create(LayoutInflater inflater, ViewGroup viewGroup, ServiceByLabHolder.OnItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_lab_service, viewGroup, false);
        return new ServiceByLabHolder(view, listener);
    }

    public interface OnItemClickListener {


    }
}

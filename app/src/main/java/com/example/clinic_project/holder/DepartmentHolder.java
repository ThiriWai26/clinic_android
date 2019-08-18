package com.example.clinic_project.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.R;

public class DepartmentHolder extends RecyclerView.ViewHolder {

    private OnItemClickListener listener;
    private ImageView imgservice;
    private TextView txtname,txtdesp;


    public DepartmentHolder(@NonNull View itemView, OnItemClickListener listener) {

        super(itemView);
        this.listener = listener;
        imgservice = itemView.findViewById(R.id.imgservice);
        txtname = itemView.findViewById(R.id.textname);
        txtdesp = itemView.findViewById(R.id.textdesp);

    }

    public static DepartmentHolder create(LayoutInflater inflater, ViewGroup parent, OnItemClickListener listener) {

        View view = inflater.inflate(R.layout.layout_department_cardview, parent, false);
        return new DepartmentHolder(view, listener);
    }

    public static void bindData() {
    }

    public interface OnItemClickListener {
    }
}

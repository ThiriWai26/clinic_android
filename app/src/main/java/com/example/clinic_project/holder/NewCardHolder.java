package com.example.clinic_project.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.clinic_project.R;

public class NewCardHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnItemClickListener listener;
    private static TextView txphoneno;
    private static TextView tvid;

    public NewCardHolder(@NonNull View itemView, OnItemClickListener listener) {
        super(itemView);
        this.listener = this.listener;

        tvid = itemView.findViewById(R.id.tvid);
        txphoneno = itemView.findViewById(R.id.txt_phoneno);

        itemView.setOnClickListener(this);
    }

    public static NewCardHolder create(LayoutInflater inflater, ViewGroup parent, OnItemClickListener listener) {

        View view = inflater.inflate(R.layout.layout_phonenumber_list, parent, false);
        return new NewCardHolder(view,listener);
    }

    public static void bindData() {
    }

    @Override
    public void onClick(View v) {
    }

    public interface OnItemClickListener {
    }
}

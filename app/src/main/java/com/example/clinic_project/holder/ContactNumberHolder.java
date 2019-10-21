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
import com.example.clinic_project.model.BuildingDetail;

public class ContactNumberHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnItemClickListener listener;
    private static TextView txphoneno;
    private static TextView tvid;

    public ContactNumberHolder(@NonNull View itemView, OnItemClickListener listener) {

        super(itemView);
        this.listener = listener;

        tvid = itemView.findViewById(R.id.tvid);
        txphoneno = itemView.findViewById(R.id.txt_phoneno);

        itemView.setOnClickListener(this);

    }

    public static ContactNumberHolder create(LayoutInflater inflater, ViewGroup parent, OnItemClickListener listener) {

        View view = inflater.inflate(R.layout.layout_phonenumber_list, parent, false);
        return new ContactNumberHolder(view,listener);
    }

    public static void bindData() {

//        txphoneno.setText(String.valueOf(buildingDetail.phoneNumber));
//        Log.e("phoneno", String.valueOf(buildingDetail.phoneNumber));

    }

    @Override
    public void onClick(View v) {
        listener.onContactNumberClick();
//        int position;
//        position = getAdapterPosition();
//        Log.e("position", String.valueOf(position));
    }

    public interface OnItemClickListener {

        void onContactNumberClick();

    }
}

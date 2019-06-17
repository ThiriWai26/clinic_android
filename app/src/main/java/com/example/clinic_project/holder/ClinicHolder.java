package com.example.clinic_project.holder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.model.Clinic;
import com.squareup.picasso.Picasso;

public class ClinicHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final OnClinicClickListener listener;
    private TextView txName, txType, txId, txAddress;
    private ImageView imageView;
    private ImageButton btnClinicDetail;

    public static ClinicHolder create(LayoutInflater inflater, ViewGroup viewGroup, OnClinicClickListener listener) {

        View view = inflater.inflate(R.layout.layout_clinic_list, viewGroup, false);
        return new ClinicHolder(view, listener);
    }

    @Override
    public void onClick(View v) {

        listener.onClinicClick(Integer.parseInt((String) txId.getText()));
        int position;
        position = getAdapterPosition();
        Log.e("position", String.valueOf(position));
    }

    public interface OnClinicClickListener {

        public void onClinicClick (int id);

    }

    public ClinicHolder (View view, OnClinicClickListener listener){

        super(view);
        this.listener = listener;
        initView(view);
        view.setOnClickListener(this);
    }

    private void initView(View view) {

        txAddress = view.findViewById(R.id.tvAddress);
        txName = view.findViewById(R.id.tvName);
        txId = view.findViewById(R.id.tvid);
        txType = view.findViewById(R.id.tvType);
        imageView = view.findViewById(R.id.profile);
        btnClinicDetail = view.findViewById(R.id.btn_clinic_detail);

        btnClinicDetail.setOnClickListener(this);
        view.setOnClickListener(this);
    }

    public void bindData(Clinic clinic) {

        txName.setText(clinic.name);
        txAddress.setText((CharSequence) clinic.towns);
        txType.setText(clinic.about);
        txId.setText(String.valueOf(clinic.id));

        Log.e("photo",clinic.photo);

        Picasso.get()
                .load("http://128.190.180.50/api/get_image/" + clinic.name)
                .resize(800,700)
                .centerCrop()
                .into(imageView);
    }


}

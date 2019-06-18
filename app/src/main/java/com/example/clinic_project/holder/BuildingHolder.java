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
import com.example.clinic_project.model.Building;
import com.squareup.picasso.Picasso;

public class BuildingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnBuildingClickListener listener;
    private TextView txName, txLocation, txId;
//    private ImageView imageViewExpand;
    private ImageView imageView;
    private ImageButton btnHospitalDetail;


    public static BuildingHolder create(LayoutInflater inflater, ViewGroup parent, OnBuildingClickListener listener) {

            View view = inflater.inflate(R.layout.building_item, parent, false);
            return new BuildingHolder(view, listener);
    }


    @Override
    public void onClick(View v) {

        listener.onBuildingClick(Integer.parseInt((String) txId.getText()));
        int position;
        position = getAdapterPosition();
        Log.e("position", String.valueOf(position));

    }

    public void bindData(Building building) {

        txName.setText(building.name);
        txLocation.setText(building.townName);
        txId.setText(String.valueOf(building.id));

        Log.e("photo",building.image);

        Picasso.get()
                .load("http://128.190.180.50/api/get_image/" + building.name)
                .resize(800,700)
                .centerCrop()
                .into(imageView);
    }


    public interface OnBuildingClickListener {

        public void onBuildingClick (int id);

    }

    public BuildingHolder(View itemView, OnBuildingClickListener listener) {

        super(itemView);
        this.listener = listener;
        initView(itemView);
        itemView.setOnClickListener(this);


    }

    private void initView(View view) {

        txLocation = view.findViewById(R.id.txLocation);
        txName = view.findViewById(R.id.txName);
        txId = view.findViewById(R.id.txid);
        imageView = view.findViewById(R.id.imageView1);
        btnHospitalDetail = view.findViewById(R.id.btn);

        btnHospitalDetail.setOnClickListener(this);
        view.setOnClickListener(this);
    }


}

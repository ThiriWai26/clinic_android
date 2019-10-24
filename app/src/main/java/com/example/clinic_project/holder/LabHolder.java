package com.example.clinic_project.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.model.Building;
import com.squareup.picasso.Picasso;

public class LabHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private OnLabClickListener listener;
    private static TextView  txName, txLocation, txId;
    private static ImageView imageView;
    private static RatingBar ratingBar;

    public LabHolder(@NonNull View itemView, LabHolder.OnLabClickListener listener) {
        super(itemView);
        this.listener = this.listener;
        initView(itemView);
        itemView.setOnClickListener(this);
    }

    private void initView(View view) {

        txLocation = view.findViewById(R.id.txLocation);
        txName = view.findViewById(R.id.txName);
        txId = view.findViewById(R.id.txid);
        imageView = view.findViewById(R.id.imageView1);
        ratingBar = view.findViewById(R.id.rating);

        view.setOnClickListener(this);

    }

    public static LabHolder create(LayoutInflater inflater, ViewGroup viewGroup, LabHolder.OnLabClickListener listener) {
        View view = inflater.inflate(R.layout.layout_lab_list, viewGroup, false);
        return new LabHolder(view, listener);

    }

    public static void bindData(Building building) {

        txName.setText(building.name);
        txLocation.setText(building.townName);
        txId.setText(String.valueOf(building.id));
        ratingBar.setRating(building.rating);

        Picasso.get()
                .load("http://192.168.100.201:8001/api/download_image/" + building.image)
                .resize(800,700)
                .centerCrop()
                .into(imageView);

        Log.e("id", String.valueOf(building.id));
        Log.e("name",building.name);
        Log.e("feature_photo",building.image);
        Log.e("town_id", String.valueOf(building.townId));
        Log.e("rating", String.valueOf(building.rating));
        Log.e("town_name",building.townName);
    }

    @Override
    public void onClick(View v) {
        listener.onLabClick(Integer.parseInt((String) txId.getText()));
        int position;
        position = getAdapterPosition();
        Log.e("position", String.valueOf(position));


    }

    public interface OnLabClickListener {
        void onLabClick(int id);
    }
}

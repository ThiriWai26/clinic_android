package com.example.clinic_project.holder;

import android.media.Rating;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic_project.R;
import com.example.clinic_project.model.Building;
import com.squareup.picasso.Picasso;

public class BuildingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnBuildingClickListener listener;
    private TextView txName, txLocation, txId;
    private ImageView imageView;
    private RatingBar rating;

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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void bindData(Building building) {

        txName.setText(building.name);
        txLocation.setText(building.townName);
        txId.setText(String.valueOf(building.id));
//        rating.setTextAlignment(building.rating);
        int numberOfStars = (int) rating.getRating();


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

    public interface OnBuildingClickListener {
        public void onBuildingClick(int id);
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
        rating = (RatingBar) view.findViewById(R.id.rating);

        view.setOnClickListener(this);

    }
}

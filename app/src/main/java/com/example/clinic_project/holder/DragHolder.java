package com.example.clinic_project.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.model.Service;
import com.example.clinic_project.service.RetrofitService;
import com.squareup.picasso.Picasso;

public class DragHolder extends RecyclerView.ViewHolder  {

    private OnItemClickListener listener;
    private TextView txName, txLocation, txId;
    private ImageView imageView;
    private RelativeLayout layout;

    public DragHolder(@NonNull View itemView, OnItemClickListener listener) {

        super(itemView);
        this.listener = listener;

        txName = itemView.findViewById(R.id.txName);
        txLocation = itemView.findViewById(R.id.txLocation);
        txId = itemView.findViewById(R.id.txid);
        imageView = itemView.findViewById(R.id.imageView1);
        layout = itemView.findViewById(R.id.layout);
    }

    public static DragHolder create(LayoutInflater inflater, ViewGroup parent, OnItemClickListener listener) {

        View view = inflater.inflate(R.layout.layout_healthdrag_item, parent, false);
        return new DragHolder(view, listener);
    }

    public void bindData(final Service service) {

        txName.setText(service.name);
        txLocation.setText(service.address);
        txId.setText(String.valueOf(service.id));
        Picasso.get()
                .load(RetrofitService.BASE_URL + "/api/download_image/"  + service.featurePhoto)
                .resize(800,700)
                .centerCrop()
                .into(imageView);

        Log.e("Id", String.valueOf(service.id));
        Log.e("Name",service.name);
        Log.e("Location",service.address);
        Log.e("FeaturePhoto", service.featurePhoto);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDragClick(service.id);
            }
        });
    }

    public interface OnItemClickListener {
        public void onDragClick(int i);
    }
}

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
import com.example.clinic_project.model.Favourite;
import com.squareup.picasso.Picasso;

public class MyFavouriteHospitalHolder extends RecyclerView.ViewHolder{

    private static TextView txLocation,txname,txid;
    private ImageView imgprofile;
    private OnItemClickListener listener;

    public MyFavouriteHospitalHolder(@NonNull View itemView, OnItemClickListener listener) {
        super(itemView);
        this.listener = listener;

        initView(itemView);
    }

    private void initView(View itemView) {

        txid = itemView.findViewById(R.id.txid);
        txname = itemView.findViewById(R.id.txName);
        txLocation = itemView.findViewById(R.id.txLocation);
        imgprofile = itemView.findViewById(R.id.imageView1);

    }

    public static void bindData(Favourite favourite) {

        txid.setText(String.valueOf(favourite.id));
        txname.setText(String.valueOf(favourite.name));
        txLocation.setText(String.valueOf(favourite.townName));
//        Picasso.get()
//                .load("http://192.168.100.201:8001/api/download_image/" + favourite.)
//                .resize(40, 40)
//                .onlyScaleDown()
//                .centerCrop()
//                .into(imgprofile);

        Log.e("name",favourite.name);
        Log.e("townName",favourite.townName);


    }

    public static MyFavouriteHospitalHolder create(LayoutInflater inflater, ViewGroup viewGroup, OnItemClickListener listener) {

        View view = inflater.inflate(R.layout.layout_favhospital_layout, viewGroup , false);
        Log.e("holder","success");
        return new MyFavouriteHospitalHolder(view,listener);
    }

    public interface OnItemClickListener{

    }
}

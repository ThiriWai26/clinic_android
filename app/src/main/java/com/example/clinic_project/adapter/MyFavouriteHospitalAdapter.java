package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.holder.MyFavouriteHospitalHolder;
import com.example.clinic_project.model.Favourite;

import java.util.ArrayList;
import java.util.List;

public class MyFavouriteHospitalAdapter extends RecyclerView.Adapter<MyFavouriteHospitalHolder> {

    List<Favourite> favouriteList;
    MyFavouriteHospitalHolder.OnItemClickListener listener;

    public MyFavouriteHospitalAdapter (MyFavouriteHospitalHolder.OnItemClickListener listener){

        favouriteList = new ArrayList<>();
        this.listener = listener;
    }


    @NonNull
    @Override
    public MyFavouriteHospitalHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return  MyFavouriteHospitalHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFavouriteHospitalHolder myFavouriteHospitalHolder, int i) {

        MyFavouriteHospitalHolder.bindData(favouriteList.get(i));

    }

    @Override
    public int getItemCount() {

        return favouriteList.size();
    }

    public void addItem(List<Favourite> favourites){

        this.favouriteList.clear();
        this.favouriteList.addAll(favourites);
        notifyDataSetChanged();

    }
}

package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.Activity.HospitalDetailActivity;
import com.example.clinic_project.holder.ContactNumberHolder;
import com.example.clinic_project.holder.DragHolder;
import com.example.clinic_project.holder.ExaminationHolder;
import com.example.clinic_project.model.BuildingDetail;
import com.example.clinic_project.model.OtherServiceDetail;
import com.example.clinic_project.model.Phones;

import java.util.ArrayList;
import java.util.List;

public class ContactNumberAdapter extends RecyclerView.Adapter<ContactNumberHolder> {

    ContactNumberHolder.OnItemClickListener listener;

    public ContactNumberAdapter(ContactNumberHolder.OnItemClickListener listener) {

        this.listener = listener;
    }

    public ContactNumberHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return ContactNumberHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactNumberHolder contactNumberHolder, int i) {

        contactNumberHolder.bindData();
    }

    public int getItemCount() {

        return 5;
    }


}

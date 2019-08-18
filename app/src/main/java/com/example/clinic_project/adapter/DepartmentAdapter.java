package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.holder.DepartmentHolder;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentHolder> {

    DepartmentHolder.OnItemClickListener listener;

    public DepartmentAdapter(DepartmentHolder.OnItemClickListener listener) {

        this.listener = listener;
    }

    @NonNull
    @Override
    public DepartmentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return DepartmentHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentHolder departmentHolder, int i) {

        DepartmentHolder.bindData();

    }

    @Override
    public int getItemCount() {

        return 5;
    }
}

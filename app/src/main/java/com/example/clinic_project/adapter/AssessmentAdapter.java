package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.fragment.FragmentAssessment;
import com.example.clinic_project.holder.AssessmentHolder;
import com.example.clinic_project.holder.ExaminationHolder;
import com.example.clinic_project.model.Service;

import java.util.ArrayList;
import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentHolder> {
    List<Service> services;
   AssessmentHolder.OnItemClickListener listener;

    public AssessmentAdapter(FragmentAssessment listener) {

        services = new ArrayList<>();
        this.listener = listener;
    }

    public void addItem(List<Service> service) {
        this.services.clear();
        this.services.addAll(service);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AssessmentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return AssessmentHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentHolder assessmentHolder, int i) {
        assessmentHolder.bindData(services.get(i));
    }

    @Override
    public int getItemCount() {
        return services.size();
    }
}





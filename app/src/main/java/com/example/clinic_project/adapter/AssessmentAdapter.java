package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.fragment.FragmentAssessment;
import com.example.clinic_project.holder.AssessmentHolder;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentHolder> {

   AssessmentHolder.OnItemClickListener listener;

    public AssessmentAdapter(FragmentAssessment listener) {

        this.listener = listener;
    }

    @NonNull
    @Override
    public AssessmentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return AssessmentHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentHolder assessmentHolder, int i) {

        AssessmentHolder.bindData();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

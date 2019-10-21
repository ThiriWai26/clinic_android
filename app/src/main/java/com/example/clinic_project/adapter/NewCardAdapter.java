package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.Activity.NewCardViewActivity;
import com.example.clinic_project.holder.AssessmentHolder;
import com.example.clinic_project.holder.NewCardHolder;

public class NewCardAdapter extends RecyclerView.Adapter<NewCardHolder> {

    NewCardHolder.OnItemClickListener listener;

    public NewCardAdapter(NewCardViewActivity listener) {

        this.listener = listener;
    }

    @NonNull
    @Override
    public NewCardHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return NewCardHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewCardHolder newCardHolder, int i) {

        NewCardHolder.bindData();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

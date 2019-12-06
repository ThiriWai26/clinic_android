package com.example.clinic_project.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.clinic_project.holder.ExaminationPhoneNumberHolder;
import com.example.clinic_project.model.OtherServiceDetail;
import com.example.clinic_project.model.Phones;

import java.util.ArrayList;
import java.util.List;

public class ExaminationPhoneNumberAdapter extends RecyclerView.Adapter<ExaminationPhoneNumberHolder> {

    List<String> phoneNumbers;
    ExaminationPhoneNumberHolder.OnItemClickListener listener;

    public ExaminationPhoneNumberAdapter(ExaminationPhoneNumberHolder.OnItemClickListener listener) {
        this.listener = listener;
        phoneNumbers = new ArrayList<>();
    }

    @NonNull
    @Override
    public ExaminationPhoneNumberHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return ExaminationPhoneNumberHolder.create(inflater,viewGroup,listener);    }

    @Override
    public void onBindViewHolder(@NonNull ExaminationPhoneNumberHolder examinationPhoneNumberHolder, int i) {
        examinationPhoneNumberHolder.bindData(phoneNumbers.get(i));
    }

    @Override
    public int getItemCount() {
        return phoneNumbers.size();
    }

    public void addItem(List<String> phoneNumbers){

        this.phoneNumbers.clear();
        this.phoneNumbers.addAll(phoneNumbers);
        notifyDataSetChanged();

    }
}

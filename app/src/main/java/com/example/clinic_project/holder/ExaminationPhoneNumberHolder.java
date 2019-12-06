package com.example.clinic_project.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.model.OtherServiceDetail;
import com.example.clinic_project.model.Phones;

public class ExaminationPhoneNumberHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnItemClickListener listener;
    private TextView txphoneno;

    public ExaminationPhoneNumberHolder(@NonNull View itemView, ExaminationPhoneNumberHolder.OnItemClickListener listener) {
        super(itemView);
        this.listener = listener;

        txphoneno = itemView.findViewById(R.id.tvphoneno);
        itemView.setOnClickListener(this);
    }

    public static ExaminationPhoneNumberHolder create(LayoutInflater inflater, ViewGroup parent, ExaminationPhoneNumberHolder.OnItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_phonenumber_list, parent, false);
        return new ExaminationPhoneNumberHolder(view,listener);
    }

    public void bindData(String phoneNumber) {

        txphoneno.setText(phoneNumber);

        Log.e("phoneno", phoneNumber);
    }

    @Override
    public void onClick(View v) {

    }

    public interface OnItemClickListener {
    }
}

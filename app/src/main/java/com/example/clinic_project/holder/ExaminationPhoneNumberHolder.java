package com.example.clinic_project.holder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.Activity.ExaminationDetailActivity;
import com.example.clinic_project.R;

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

    public void bindData(final String phoneNumber) {

        txphoneno.setText(phoneNumber);
        Log.e("phoneno", phoneNumber);

//        txphoneno.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("phone_no",String.valueOf(phoneNumber));
//                Intent intent = new Intent(String.valueOf(itemView.getContext()));
//                intent.putExtra("PhoneNumber", String.valueOf(phoneNumber));
//                Log.e("Phone_Number", String.valueOf(phoneNumber));
//                itemView.getContext().startActivity(intent);
//
//            }
//        });

    }

    @Override
    public void onClick(View v) {
        listener.onExaminationPhoneNumberClick(txphoneno);
        Log.e("listener", String.valueOf(txphoneno));
    }

    public interface OnItemClickListener {
//        public void onExaminationPhoneNumberClick(int id);

        void onExaminationPhoneNumberClick(TextView txphoneno);
    }
}

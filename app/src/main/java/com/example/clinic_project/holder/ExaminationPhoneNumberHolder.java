package com.example.clinic_project.holder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic_project.Activity.ExaminationDetailActivity;
import com.example.clinic_project.R;

public class ExaminationPhoneNumberHolder extends RecyclerView.ViewHolder  {

    private OnItemClickListener listener;
    private TextView txphoneno;
    private RelativeLayout layout;

    public ExaminationPhoneNumberHolder(@NonNull View itemView, ExaminationPhoneNumberHolder.OnItemClickListener listener) {
        super(itemView);
        this.listener = listener;

        txphoneno = itemView.findViewById(R.id.tvphoneno);
        layout = itemView.findViewById(R.id.layout);
    }

    public static ExaminationPhoneNumberHolder create(LayoutInflater inflater, ViewGroup parent, ExaminationPhoneNumberHolder.OnItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_phonenumber_list, parent, false);
        return new ExaminationPhoneNumberHolder(view,listener);
    }

    public void bindData(final String phoneNumber) {

        txphoneno.setText(phoneNumber);
        Log.e("phoneno", phoneNumber);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("layoutClick","ok");
                listener.onExaminationPhoneNumberClick(txphoneno);
                Log.e("listener", String.valueOf(txphoneno));

                try {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                    dialIntent.setData(Uri.parse("tel:"+ phoneNumber));
                    Log.e("phno", String.valueOf(phoneNumber));
                    itemView.getContext().startActivity(dialIntent);

                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public interface OnItemClickListener {
//        public void onExaminationPhoneNumberClick(int id);
        void onExaminationPhoneNumberClick(TextView txphoneno);

    }
}

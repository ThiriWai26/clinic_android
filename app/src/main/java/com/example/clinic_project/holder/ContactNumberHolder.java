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

public class ContactNumberHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnItemClickListener listener;
    private TextView txphoneno;
    private TextView tvid;

    public ContactNumberHolder(@NonNull View itemView, OnItemClickListener listener) {

        super(itemView);
        this.listener = listener;

        tvid = itemView.findViewById(R.id.tvid);
        txphoneno = itemView.findViewById(R.id.tvphoneno);

        itemView.setOnClickListener(this);

    }

    public static ContactNumberHolder create(LayoutInflater inflater, ViewGroup parent, OnItemClickListener listener) {

        View view = inflater.inflate(R.layout.layout_phonenumber_list, parent, false);
        return new ContactNumberHolder(view,listener);
    }

    public void bindData() {

//        txphoneno.setText(String.valueOf(otherServiceDetail.phoneNumber));
//        Log.e("phoneno", String.valueOf(otherServiceDetail.phoneNumber));

    }

    @Override
    public void onClick(View v) {
//        listener.onContactNumberClick(Integer.parseInt((String) tvid.getText()));
//        int position;
//        position = getAdapterPosition();
//        Log.e("position", String.valueOf(position));
    }

    public interface OnItemClickListener {

        void onContactNumberClick(int i);

    }
}

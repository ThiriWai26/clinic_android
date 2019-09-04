package com.example.clinic_project.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.R;

public class RegistrationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnItemClickListener listener;
    private TextView txName, txLocation, txId;
    private ImageView imageView;

    public RegistrationHolder(@NonNull View itemView, OnItemClickListener listener) {

        super(itemView);
        this.listener = listener;

        txName = itemView.findViewById(R.id.txName);
        txLocation = itemView.findViewById(R.id.txLocation);
        txId = itemView.findViewById(R.id.txid);
        imageView = itemView.findViewById(R.id.imageView1);

        itemView.setOnClickListener(this);
    }

    public static RegistrationHolder create(LayoutInflater inflater, ViewGroup parent, OnItemClickListener listener) {

        View view = inflater.inflate(R.layout.layout_healthregister_item, parent, false);
        return new RegistrationHolder(view, (RegistrationHolder.OnItemClickListener) listener);
    }

    public static void bindData() {
    }

    @Override
    public void onClick(View v) {
        listener.onRegistrationClick();
    }

    public interface OnItemClickListener {

        public void onRegistrationClick ();

    }
}

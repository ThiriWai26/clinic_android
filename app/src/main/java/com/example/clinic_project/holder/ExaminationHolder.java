package com.example.clinic_project.holder;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.R;

public class ExaminationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnItemClickListener listener;
    private TextView txName, txLocation, txId;
    private ImageView imageView;


    public ExaminationHolder(@NonNull View itemView, OnItemClickListener listener) {

        super(itemView);
        this.listener = listener;

        txName = itemView.findViewById(R.id.txName);
        txLocation = itemView.findViewById(R.id.txLocation);
        txId = itemView.findViewById(R.id.txid);
        imageView = itemView.findViewById(R.id.imageView1);

        itemView.setOnClickListener(this);

    }

    public static ExaminationHolder create(LayoutInflater inflater, ViewGroup parent, OnItemClickListener listener) {

        View view = inflater.inflate(R.layout.layout_healthexam_item, parent, false);
        return new ExaminationHolder(view, (ExaminationHolder.OnItemClickListener) listener);
    }

    public static void bindData() {
    }

    @Override
    public void onClick(View v) {

        listener.onExaminationClick();
    }

    public interface OnItemClickListener {

        public void onExaminationClick ();

    }
}

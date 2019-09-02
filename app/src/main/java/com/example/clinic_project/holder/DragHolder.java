package com.example.clinic_project.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.R;

public class DragHolder extends RecyclerView.ViewHolder {

    private OnItemClickListener listener;
    private TextView txName, txLocation, txId;
    private ImageView imageView;

    public DragHolder(@NonNull View itemView, OnItemClickListener listener) {

        super(itemView);
        this.listener = listener;

        txName = itemView.findViewById(R.id.txName);
        txLocation = itemView.findViewById(R.id.txLocation);
        txId = itemView.findViewById(R.id.txid);
        imageView = itemView.findViewById(R.id.imageView1);

    }

    public static DragHolder create(LayoutInflater inflater, ViewGroup parent, OnItemClickListener listener) {

        View view = inflater.inflate(R.layout.layout_healthdrag_item, parent, false);
        return new DragHolder(view, (DragHolder.OnItemClickListener) listener);
    }

    public static void bindData() {
    }

    public interface OnItemClickListener {
    }
}

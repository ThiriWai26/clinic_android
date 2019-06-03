package com.example.clinic_project.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.model.SpecializationList;
import com.example.clinic_project.service.RetrofitService;


public class SpecializationHolder extends RecyclerView.ViewHolder {

    private SpecializationHolder.OnHolderItemClickListener listener;
    private RetrofitService service=new RetrofitService();
    private TextView specialName;
    private TextView specialId;
    private LinearLayout linearLayout;

    public void bindData(SpecializationList specializationList) {

        specialId.setText(String.valueOf(specializationList.id));
        specialName.setText(specializationList.name);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onHolderitemClick(Integer.parseInt(specialId.getText().toString()));
            }
        });
    }

    public interface OnHolderItemClickListener {
        public void onHolderitemClick(int id);
    }

    public SpecializationHolder(@NonNull View itemView, SpecializationHolder.OnHolderItemClickListener listener ) {
        super(itemView);

        this.listener=listener;
        initViewHolder(itemView);
    }

    private void initViewHolder(View itemView) {

     specialName=itemView.findViewById(R.id.specialName);
     specialId=itemView.findViewById(R.id.speicalId);
     linearLayout=itemView.findViewById(R.id.special);

    }

    public static SpecializationHolder create(LayoutInflater inflater, ViewGroup parent, SpecializationHolder.OnHolderItemClickListener listener){
        View view=inflater.inflate(R.layout.layout_special_item,parent,false);
        return  new SpecializationHolder(view,listener);
    }
}


package com.example.clinic_project.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;




import com.example.clinic_project.R;
import com.example.clinic_project.model.Doctor;


public class DoctorHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnDoctorClickListener listener;

    private TextView tvname, tvSpecial,  tvId;
    private ImageView imgProfile;
    private ImageButton btnDoctorDetail;

    public interface OnDoctorClickListener {
        public void onDoctorClick (int id);
    }

    public DoctorHolder(@NonNull View itemView, OnDoctorClickListener listener) {

        super(itemView);
        this.listener = listener;
        initView (itemView);

        itemView.setOnClickListener(this);

    }

    private void initView(View view) {

        tvId = view.findViewById(R.id.tvid);
        tvname = view.findViewById(R.id.tvName);
        tvSpecial = view.findViewById(R.id.tvType);
        imgProfile = view.findViewById(R.id.profile);
        btnDoctorDetail = view.findViewById(R.id.btn_doctor_detail);

        btnDoctorDetail.setOnClickListener(this);
        view.setOnClickListener(this);

    }


    public void bindData(Doctor doctor) {

        tvId.setText(String.valueOf(doctor.id));
        tvname.setText(doctor.name);

        Picasso.get()
                .load("http://192.168.100.201:8001/api/download_image/" + doctor.photo)
                .resize(40, 40)
                .onlyScaleDown()
                .centerCrop()
                .into(imgProfile);

        String special = doctor.specialists.get(0);
        for (int i = 1; i < doctor.specialists.size(); i++) {
            special += ", " + doctor.specialists.get(i);
        }

        tvSpecial.setText(special);

        Log.e("id", String.valueOf(doctor.id));
        Log.e("name",doctor.name);
        Log.e("photo",doctor.photo);
        Log.e("specialist", String.valueOf(doctor.specialists));

    }

    @Override
    public void onClick(View v) {

        listener.onDoctorClick(Integer.parseInt((String) tvId.getText()));
        int position;
        position = getAdapterPosition();
        Log.e("position", String.valueOf(position));

    }

    public static DoctorHolder create(LayoutInflater inflater, ViewGroup parent, OnDoctorClickListener listener) {
        View view = inflater.inflate(R.layout.layout_doctor_list, parent, false);
        return new DoctorHolder(view, listener);
    }
}

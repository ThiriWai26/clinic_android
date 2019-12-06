package com.example.clinic_project.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.clinic_project.service.RetrofitService;
import com.squareup.picasso.Picasso;




import com.example.clinic_project.R;
import com.example.clinic_project.model.Doctor;


public class DoctorHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnDoctorClickListener listener;

    private TextView tvname,tvId,tvtype;
    private ImageView featurephoto,profile;
    private RatingBar ratingBar;

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
        tvtype = view.findViewById(R.id.tvType);
        featurephoto = view.findViewById(R.id.featuredphoto);
        profile = view.findViewById(R.id.profile);
        ratingBar = view.findViewById(R.id.rating);

        view.setOnClickListener(this);
    }

    public void bindData(Doctor doctor) {

        tvId.setText(String.valueOf(doctor.id));
        tvname.setText(doctor.name);

//        String specialis= doctor.specialists.get(0);
//        for(int i=1;i<doctor.specialists.size();i++){
//            specialis+=","+doctor.specialists.get(i);
//        }
//        tvtype.setText(specialis);

//        Picasso.get()
//                .load(RetrofitService.BASE_URL+ "/api/download_image/" + doctor.photo)
//                .resize(800,700)
//                .centerCrop()
//                .into(featurephoto);


        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + doctor.photo).into(profile);

        Log.e("id", String.valueOf(doctor.id));
        Log.e("name",doctor.name);
        Log.e("specialists", String.valueOf(doctor.specialists));
        Log.e("profile",doctor.photo);

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

package com.example.clinic_project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.DoctorDetailResponse;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.fragment.FragmentDoctor;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorDetailActivity extends AppCompatActivity  {

    private RetrofitService service;
    private String token;
    private ImageView featurephoto,profile,imgback,imgfav;
    private TextView tvName,tvtown,tvabout,tvspecial;
    private RatingBar ratingBar;
    private Button button;
    private int doctorId = -1;
    private FloatingActionButton btnBook;
    boolean isFavourite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        initActivity();

    }

    private void initActivity() {

        service = new RetrofitService();
        token = Token.MyToken.getToken();

        Log.e("doctordetailtoken",token);

        tvName = findViewById(R.id.tvName);
        tvtown = findViewById(R.id.tvTown);
        tvabout = findViewById(R.id.tvabout);
        tvspecial = findViewById(R.id.tvspecialists);
        featurephoto=findViewById(R.id.featuredphoto);
        imgback = findViewById(R.id.imgback);
        profile = findViewById(R.id.Profile);
        imgfav = findViewById(R.id.imgfav);
        button = findViewById(R.id.btn);
        ratingBar = findViewById(R.id.rating);

        Bundle bundle = getIntent().getExtras();
        doctorId = bundle.getInt("doctorId");
        Log.e("doctorId",String.valueOf(doctorId));

        getDoctorDetail();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CalenderViewActivity.class);
                intent.putExtra("doctorId", doctorId);
                intent.putExtra("Token", token);
                Log.e("doctorId",String.valueOf(doctorId));

                startActivity(intent);
            }
        });

        imgfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavourite){
                    imgfav.setBackgroundResource(R.drawable.favouritewhite);
                    isFavourite=false;
                }
                else {
                    imgfav.setBackgroundResource(R.drawable.favouriteblack1);
                    isFavourite=true;
                }
            }
        });

    }

    private void getDoctorDetail() {

        Log.e("getDoctorDetail","success");
        Api doctorDetailApi = service.getRetrofitService().create(Api.class);
        doctorDetailApi.getDoctorDetail(token,doctorId).enqueue(new Callback<DoctorDetailResponse>() {
            @Override
            public void onResponse(Call<DoctorDetailResponse> call, Response<DoctorDetailResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        Log.e("response.body","success");

                        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + response.body().doctorDetail.photo).into(featurephoto);
                        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + response.body().doctorDetail.photo).into(profile);
//                        String clinics=response.body().doctorDetail.clinics.get(0);
//                        for(int i=1;i<response.body().doctorDetail.clinics.size();i++){
//                            clinics+=","+response.body().doctorDetail.clinics.get(i);
//                        }
//                        tvclinic.setText(clinics);
                        tvName.setText(response.body().doctorDetail.name);
                        tvabout.setText(response.body().doctorDetail.about);
//                        ratingBar.setRating(response.body().doctorDetail.rating);

                        String towns=response.body().doctorDetail.towns.get(0);
                        for(int i=1;i<response.body().doctorDetail.towns.size();i++){
                            towns+=","+response.body().doctorDetail.towns.get(i);}
                        tvtown.setText(towns);

                        String specialis=response.body().doctorDetail.specialists.get(0);
                        for(int i=1;i<response.body().doctorDetail.specialists.size();i++){
                            specialis+=","+response.body().doctorDetail.specialists.get(i);
                        }
                        tvspecial.setText(specialis);

                        Log.e("Name", response.body().doctorDetail.name);
                        Log.e("FeaturePhoto", response.body().doctorDetail.photo);
                        Log.e("Profile", response.body().doctorDetail.photo);
                        Log.e("Town", String.valueOf(response.body().doctorDetail.towns));
                        Log.e("About", response.body().doctorDetail.about);
                        Log.e("Specialists", String.valueOf(response.body().doctorDetail.specialists));
                    }
                    else{
                         Log.e("response.body","fail");
                    }
                }
                else {
                    Log.e("response","fail");
                }
            }

            @Override
            public void onFailure(Call<DoctorDetailResponse> call, Throwable t) {
                Log.e("failure",t.toString());
            }
        });

    }

    public void onBackDoctorList(View view) {
        finish();
    }
}

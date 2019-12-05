package com.example.clinic_project.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.OtherServiceDetailResponse;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.fragment.FragmentRegistration;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistrationDetailActivity extends AppCompatActivity {

    private RetrofitService service;
    private ImageView featurephoto, profile, imgback;
    private TextView tvname,tvtown,tvaddress,tvabout,tvstarttime,tvendtime;

    private String token = null;
    private int serviceId = -1;
    private String type = "pharmacy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_detail);

        initActivity();
    }

    @SuppressLint("WrongViewCast")
    private void initActivity() {

        service = new RetrofitService();
        featurephoto = findViewById(R.id.imageView);
        profile = findViewById(R.id.profile);
        imgback = findViewById(R.id.imgback);
        tvname = findViewById(R.id.tvName);
        tvtown = findViewById(R.id.tvTown);
        tvaddress = findViewById(R.id.tvaddress);
        tvabout = findViewById(R.id.tvabout);
        tvstarttime = findViewById(R.id.tvstarttime);
        tvendtime = findViewById(R.id.tvendtime);
        token = Token.MyToken.getToken();

        Bundle bundle = getIntent().getExtras();
        serviceId = bundle.getInt("serviceId");
        Log.e("serviceId", String.valueOf(serviceId));

        getRegistrationDetail();
    }

    private void getRegistrationDetail() {
        Log.e("getRegistrationDetail","success");
        Api registrationDetailApi = service.getRetrofitService().create(Api.class);
        registrationDetailApi.getOtherServiceDetail(token,type,serviceId).enqueue(new Callback<OtherServiceDetailResponse>() {
            @Override
            public void onResponse(Call<OtherServiceDetailResponse> call, Response<OtherServiceDetailResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess) {
                        Log.e("response.body", "success");

                        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + response.body().otherServiceDetail.featurePhoto).into(featurephoto);
                        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + response.body().otherServiceDetail.profile)
                                .resize(40,40)
                                .onlyScaleDown()
                                .centerCrop()
                                .into(profile);

                        tvname.setText(response.body().otherServiceDetail.name);
                        tvtown.setText(response.body().otherServiceDetail.townName);
                        tvaddress.setText(response.body().otherServiceDetail.address);
                        tvabout.setText(response.body().otherServiceDetail.about);
                        tvstarttime.setText(String.valueOf(response.body().otherServiceDetail.startTime));
                        tvendtime.setText(String.valueOf(response.body().otherServiceDetail.endTime));

                        Log.e("Name", response.body().otherServiceDetail.name);
                        Log.e("Town", String.valueOf(response.body().otherServiceDetail.townName));
                        Log.e("Address", response.body().otherServiceDetail.address);
                        Log.e("About", response.body().otherServiceDetail.about);
                        Log.e("StartTime", String.valueOf(response.body().otherServiceDetail.startTime));
                        Log.e("EndTime", String.valueOf(response.body().otherServiceDetail.endTime));
                        Log.e("FeaturePhoto",response.body().otherServiceDetail.featurePhoto);
                        Log.e("Profile",response.body().otherServiceDetail.profile);
                    }
                    else {
                        Log.e("response.body.isSuccess","false");
                    }
                }
                else {
                    Log.e("response.body","fail");
                }
            }

            @Override
            public void onFailure(Call<OtherServiceDetailResponse> call, Throwable t) {
                Log.e("failure",t.toString());
            }
        });
    }


    public void onBackRegistrationClick(View view) {
        finish();
    }
}

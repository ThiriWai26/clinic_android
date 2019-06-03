package com.example.clinic_project.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.DoctorDetailResponse;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorDetailActivity extends AppCompatActivity {

    private RetrofitService service;
    private String token;
    private de.hdodenhof.circleimageview.CircleImageView imageView;
    private TextView tvName,tvType,about,tvclinic,tvtown;
    private int doctorId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_doctor_detail);

        initActivity();
    }

    private void initActivity() {

        service = new RetrofitService();
        token = Token.MyToken.getToken();

        tvName = findViewById(R.id.tvName);
        tvType = findViewById(R.id.tvType);
        imageView=findViewById(R.id.profile);
        Bundle bundle = getIntent().getExtras();
        doctorId = bundle.getInt("doctorId");
        Log.e("doctorId",String.valueOf(doctorId));

        about=findViewById(R.id.about);
        tvclinic=findViewById(R.id.clinic);
        tvtown=findViewById(R.id.town);
        getDoctorDetail();

    }

    private void getDoctorDetail() {

        Log.e("getDoctorDetail","success");
        Api doctorDetailApi = service.getRetrofitService().create(Api.class);
        doctorDetailApi.getDoctorDetail(token,doctorId).enqueue(new Callback<DoctorDetailResponse>() {
            @Override
            public void onResponse(Call<DoctorDetailResponse> call, Response<DoctorDetailResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        Picasso.get()
                                .load("http://128.199.180.50/api/get_image/" + response.body().doctorDetail.photo)
                                .resize(40, 40)
                                .onlyScaleDown()
                                .centerCrop()
                                .into(imageView);

                        String specialis=response.body().doctorDetail.specialists.get(0);
                        String clinics=response.body().doctorDetail.clinics.get(0);
                        String towns=response.body().doctorDetail.towns.get(0);

                        for(int i=1;i<response.body().doctorDetail.specialists.size();i++){
                            specialis+=","+response.body().doctorDetail.specialists.get(i);
                        }


                        for(int i=1;i<response.body().doctorDetail.clinics.size();i++){
                            clinics+=","+response.body().doctorDetail.clinics.get(i);
                        }


                        for(int i=1;i<response.body().doctorDetail.towns.size();i++){
                            towns+=","+response.body().doctorDetail.towns.get(i);
                        }

                        about.setText(response.body().doctorDetail.about);
                        tvclinic.setText(clinics);
                        tvtown.setText(towns);
                        tvName.setText(response.body().doctorDetail.name);
                    }
                }
            }

            @Override
            public void onFailure(Call<DoctorDetailResponse> call, Throwable t) {

            }
        });


    }
}

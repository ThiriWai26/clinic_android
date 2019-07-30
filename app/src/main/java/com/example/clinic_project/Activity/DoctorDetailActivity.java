package com.example.clinic_project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class DoctorDetailActivity extends AppCompatActivity  {

    private RetrofitService service;
    private String token;
    private ImageView imageView,imgback,imgfav;
    private TextView tvName,tvType,tvabout,textabout,tvspecial;
    private Button button;
    private int doctorId = -1;
    private FloatingActionButton btnBook;
    boolean isFavourite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        initActivity();

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DrawerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initActivity() {

        service = new RetrofitService();
        token = Token.MyToken.getToken();

        Log.e("doctordetailtoken",token);

        tvName = findViewById(R.id.tvName);
        tvType = findViewById(R.id.tvType);
        tvabout = findViewById(R.id.tvabout);
        textabout = findViewById(R.id.textabout);
        tvType = findViewById(R.id.tvType);
        button = findViewById(R.id.btnDoctor);
        imageView=findViewById(R.id.imageView);
        imgback = findViewById(R.id.imgback);
        imgfav = findViewById(R.id.imgfav);

        btnBook=findViewById(R.id.fab);

        Bundle bundle = getIntent().getExtras();
        doctorId = bundle.getInt("doctorId");
        Log.e("doctorId",String.valueOf(doctorId));

        getDoctorDetail();

        btnBook.setOnClickListener(new View.OnClickListener() {
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
                        Picasso.get()
                                .load("http://128.199.180.50/api/get_image/" + response.body().doctorDetail.photo)
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

                        textabout.setText(response.body().doctorDetail.about);
//                        tvclinic.setText(clinics);
//                        tvtown.setText(towns);
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

package com.example.clinic_project.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.BuildingDetailResponse;
import com.example.clinic_project.Response.FavouriteListResponse;
import com.example.clinic_project.Response.FavouriteResponse;
import com.example.clinic_project.Response.RatingResponse;
import com.example.clinic_project.Response.UnsetFavouriteResponse;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HospitalDetailActivity extends AppCompatActivity {

    private RetrofitService service;
    private String token;
    private ImageView imageView,imgback,imgphone,imgprofile,imgSpecial,imgmap,imgfav,imgservice;
    private TextView address,txtname,txtlocation,textabout,textviewmap,textservice;
    private RelativeLayout hservice,department;
    private Button bookanappointment;

    private int buildingId = -1;
    private String type = "hospitals";
    private boolean isFavourite;

    private int favouriteableId = -1;
    private String favouriteableType = "hospitals";

    private int rateableId = -1;
    private String rateableType = "hospitals";
    private int value = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detail);

        initActivity();

    }

    @SuppressLint("WrongViewCast")
    private void initActivity() {

        imageView = findViewById(R.id.imageView);
        imgprofile = findViewById(R.id.profile);
        imgSpecial = findViewById(R.id.imgsetting);
        bookanappointment = findViewById(R.id.btn);
        address = findViewById(R.id.address);
        txtname = findViewById(R.id.tvName);
        imgback = findViewById(R.id.imgback);
        txtlocation = findViewById(R.id.textLocation);
        textabout = findViewById(R.id.textabout);
        imgphone = findViewById(R.id.phone);
        imgfav = findViewById(R.id.imgfav);
        imgservice = findViewById(R.id.imageservice);
        textservice = findViewById(R.id.txservice);
        token = Token.MyToken.getToken();
        service = new RetrofitService();
        hservice= findViewById(R.id.relativeservice);
        department = findViewById(R.id.relativedepartment);

        Bundle bundle = getIntent().getExtras();
        buildingId = bundle.getInt("buildingId");
        Log.e("buildingId",String.valueOf(buildingId));
        getBuildingDetail();

        favouriteableId = bundle.getInt("buildingId");
        Log.e("favouriteableId",String.valueOf(favouriteableId));

        rateableId = bundle.getInt("buildingId");
        Log.e("rateableId",String.valueOf(rateableId));

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), HospitalListActivity.class);
//                startActivity(intent);
                finish();
            }
        });

//        imgmap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

        imgphone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PhoneActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bookanappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DoctorsByHospital.class);
                startActivity(intent);
            }
        });

       imgfav.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if (isFavourite){
                   imgfav.setBackgroundResource(R.drawable.favouritewhite);
                   isFavourite=false;
                   unsetFavourite();
               }

               else {
                   imgfav.setBackgroundResource(R.drawable.favouritered);
                   isFavourite=true;
                   setFavourite();

               }

           }
       });

        hservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ServiceActivity.class);
                startActivity(intent);
            }
        });

        department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DepartmentActivity.class);
                startActivity(intent);
            }
        });


    }

    private void getBuildingDetail() {

        Log.e("Building_detail","successs");
        Api buildingDetailApi = service.getRetrofitService().create(Api.class);
        buildingDetailApi.getBuildingDetail(token, type, buildingId).enqueue(new Callback<BuildingDetailResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<BuildingDetailResponse> call, Response<BuildingDetailResponse> response) {
                Log.e("response.body","success");
                if(response.body().isSuccess){
                    if(response.isSuccessful()){

                        Picasso.get()
                                .load("http://192.168.100.201:8001/api/download_image/" + response.body().buildingDetails.featurePhoto)
                                .into(imageView);

//                        Picasso.get()
//                                .load("http://192.168.100.201:8001/api/download_image/" + response.body().buildingDetails.photos)
//                                .resize(40, 40)
//                                .onlyScaleDown()
//                                .centerCrop()
//                                .into(imgprofile);

                        txtname.setText(response.body().buildingDetails.name);
                        address.setText(response.body().buildingDetails.townName);
                        txtlocation.setText(response.body().buildingDetails.address);
                        textabout.setText(response.body().buildingDetails.about);

                        Log.e("featured photo",response.body().buildingDetails.featurePhoto);
                        Log.e("photo",response.body().buildingDetails.photos);
                        Log.e("name",response.body().buildingDetails.name);
                        Log.e("address",response.body().buildingDetails.address);
                        Log.e("location",response.body().buildingDetails.townName);
                        Log.e("about",response.body().buildingDetails.about);

                        getRating();

                    }
                    else {
                        Log.e("response.body","fail");
                    }
                }else {
                    Log.e("response","fail");
                }

            }

            @Override
            public void onFailure(Call<BuildingDetailResponse> call, Throwable t) {
                Log.e("onfailure", t.toString());

            }
        });

    }

    private void setFavourite(){

        Log.e("setFavourite","success");
        Api setFavouriteApi = service.getRetrofitService().create(Api.class);
        setFavouriteApi.setFavourite(token, favouriteableId, favouriteableType).enqueue(new Callback<FavouriteResponse>() {
            @Override
            public void onResponse(Call<FavouriteResponse> call, Response<FavouriteResponse> response) {
               if(response.isSuccessful()){
                   if(response.body().isSuccess){
                       Log.e("response.body","success");
                       Toast.makeText(getApplicationContext(),"Set Favourite", Toast.LENGTH_LONG).show();
                   }
                   else {
                       Toast.makeText(getApplicationContext(),response.body().error_message,Toast.LENGTH_LONG).show();
                   }
               }else {
                   Log.e("response","fail");
               }
            }

            @Override
            public void onFailure(Call<FavouriteResponse> call, Throwable t) {

            }
        });
    }

    private void unsetFavourite(){

        Log.e("unsetFavourite","success");
        Api unsetFavouriteApi = service.getRetrofitService().create(Api.class);
        unsetFavouriteApi.unsetFavourite(token, favouriteableId, favouriteableType).enqueue(new Callback<UnsetFavouriteResponse>() {
            @Override
            public void onResponse(Call<UnsetFavouriteResponse> call, Response<UnsetFavouriteResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        Log.e("response.body","success");
                        Toast.makeText(getApplicationContext(),"Unset Favourite", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Log.e("response.body","fail");
                        Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Log.e("response","fail");
                }
            }

            @Override
            public void onFailure(Call<UnsetFavouriteResponse> call, Throwable throwable) {
                Log.e("onfailure",throwable.toString());
            }
        });
    }

    private void getRating(){

        Log.e("getRating","success");
        Api raingApi = service.getRetrofitService().create(Api.class);
        raingApi.getRating(token,value,rateableId,rateableType).enqueue(new Callback<RatingResponse>() {
            @Override
            public void onResponse(Call<RatingResponse> call, Response<RatingResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        Log.e("response.body","success");
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Log.e("response.body","fail");
                        Toast.makeText(getApplicationContext(), response.body().errorMessage, Toast.LENGTH_LONG).show();
                    }
                }else{
                    Log.e("response","fail");
                }
            }

            @Override
            public void onFailure(Call<RatingResponse> call, Throwable throwable) {

            }
        });

    }



}

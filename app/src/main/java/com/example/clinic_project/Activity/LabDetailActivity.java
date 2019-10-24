package com.example.clinic_project.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.clinic_project.Response.FavouriteResponse;
import com.example.clinic_project.Response.RatingResponse;
import com.example.clinic_project.Response.UnsetFavouriteResponse;
import com.example.clinic_project.adapter.ContactNumberAdapter;
import com.example.clinic_project.adapter.LabContactNumberAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.LabContactNumberHolder;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;
import com.squareup.picasso.Picasso;

import java.nio.channels.InterruptedByTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LabDetailActivity extends AppCompatActivity implements LabContactNumberHolder.OnItemClickListener {

    private ImageView imgfeature, imgback, imgprofile, imgfav;
    private TextView tvname, tvlocation, tvaddress, tvabout, tvcancel, tvok;
    private RatingBar ratingBar;
    private RelativeLayout service, department;
    private Button btnappointment;
    private RetrofitService retrofitService;
    private LabContactNumberAdapter adapter;
    private RecyclerView recyclerView;
    private String token = null;
    private int buildingId = -1;
    private String type = "labs";
    private boolean isFavourite;
    private int favouriteableId = -1;
    private String favouriteableType = "labs";
    private int rateableId = -1;
    private String rateableType = "labs";
    private int value = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_detail);

        initActivity();
    }

    private void initActivity() {

        imgfeature = findViewById(R.id.imgfeaturedphoto);
        imgback = findViewById(R.id.imgback);
        imgprofile = findViewById(R.id.profile);
        imgfav = findViewById(R.id.imgfav);
        tvname = findViewById(R.id.tvName);
        tvlocation = findViewById(R.id.location);
        tvaddress = findViewById(R.id.address);
        tvabout = findViewById(R.id.about);
        ratingBar = findViewById(R.id.rating);
        service = findViewById(R.id.relativeservice);
        department = findViewById(R.id.relativedepartment);
        btnappointment = findViewById(R.id.btnbook);
        retrofitService = new RetrofitService();
        token = Token.MyToken.getToken();

        Bundle bundle = getIntent().getExtras();
//        buildingId = bundle.getInt("buildingId");
        Log.e("buildingId",String.valueOf(buildingId));

        getLabDetail();

        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ServiceByLabActivity.class);
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

    private void getLabDetail() {
        Log.e("Lab_Detail","success");
        Api labDetailApi = retrofitService.getRetrofitService().create(Api.class);
        labDetailApi.getBuildingDetail(token,type,buildingId).enqueue(new Callback<BuildingDetailResponse>() {
            @Override
            public void onResponse(Call<BuildingDetailResponse> call, Response<BuildingDetailResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess){
                        Log.e("response.body","success");

                        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + response.body().buildingDetails.featurePhoto).into(imgfeature);

//                        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + response.body().buildingDetails.photos)
//                                .resize(40, 40)
//                                .onlyScaleDown()
//                                .centerCrop()
//                                .into(imgprofile);

                        tvname.setText(response.body().buildingDetails.name);
                        tvaddress.setText(response.body().buildingDetails.townName);
                        tvlocation.setText(response.body().buildingDetails.address);
                        tvabout.setText(response.body().buildingDetails.about);
                        ratingBar.setRating(response.body().buildingDetails.rating);

                        Log.e("featured photo",response.body().buildingDetails.featurePhoto);
                        Log.e("photo",response.body().buildingDetails.photos);
                        Log.e("name",response.body().buildingDetails.name);
                        Log.e("address",response.body().buildingDetails.address);
                        Log.e("location",response.body().buildingDetails.townName);
                        Log.e("about",response.body().buildingDetails.about);
                        Log.e("rating", String.valueOf(response.body().buildingDetails.rating));

//                        getRating();
                    } else {
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

//    private void getRating() {
//
//        Log.e("getRating","success");
//        Api raingApi = service.getRetrofitService().create(Api.class);
//        raingApi.getRating(token,value,rateableId,rateableType).enqueue(new Callback<RatingResponse>() {
//            @Override
//            public void onResponse(Call<RatingResponse> call, Response<RatingResponse> response) {
//                if(response.isSuccessful()){
//                    if(response.body().isSuccess){
//                        Log.e("response.body","success");
//                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
//                    }
//                    else{
//                        Log.e("response.body","fail");
//                        Toast.makeText(getApplicationContext(), response.body().errorMessage, Toast.LENGTH_LONG).show();
//                    }
//                }else{
//                    Log.e("response","fail");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RatingResponse> call, Throwable throwable) {
//                Log.e("onfailure",throwable.toString());
//            }
//        });
//    }


    public void onbackClick(View view) {
        finish();
    }

    public void onBookAppointmentClick(View view) {
        Intent intent = new Intent (getApplicationContext(), BookingLabActivity.class);
        startActivity(intent);
    }

    public void onFavouriteClick(View view) {

        if (isFavourite){
            imgfav.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
            isFavourite=false;
//            unsetFavourite();
        }

        else {
            imgfav.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
            isFavourite=true;
//            setFavourite();

        }
    }

//    private void unsetFavourite() {
//        Log.e("setFavourite","success");
//        Api setFavouriteApi = RetrofitService.getRetrofitService().create(Api.class);
//        setFavouriteApi.setFavourite(token, favouriteableId, favouriteableType).enqueue(new Callback<FavouriteResponse>() {
//            @Override
//            public void onResponse(Call<FavouriteResponse> call, Response<FavouriteResponse> response) {
//                if(response.isSuccessful()){
//                    if(response.body().isSuccess){
//                        Log.e("response.body","success");
//                        Toast.makeText(getApplicationContext(),"Set Favourite", Toast.LENGTH_LONG).show();
//                    }
//                    else {
//                        Toast.makeText(getApplicationContext(),response.body().error_message,Toast.LENGTH_LONG).show();
//                    }
//                }else {
//                    Log.e("response","fail");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<FavouriteResponse> call, Throwable t) {
//                Log.e("onfailure",t.toString());
//            }
//        });
//    }
//
//    private void setFavourite(){
//        Log.e("unsetFavourite","success");
//        Api unsetFavouriteApi = service.getRetrofitService().create(Api.class);
//        unsetFavouriteApi.unsetFavourite(token, favouriteableId, favouriteableType).enqueue(new Callback<UnsetFavouriteResponse>() {
//            @Override
//            public void onResponse(Call<UnsetFavouriteResponse> call, Response<UnsetFavouriteResponse> response) {
//                if(response.isSuccessful()){
//                    if(response.body().isSuccess){
//                        Log.e("response.body","success");
//                        Toast.makeText(getApplicationContext(),"Unset Favourite", Toast.LENGTH_LONG).show();
//                    }
//                    else {
//                        Log.e("response.body","fail");
//                        Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_LONG).show();
//                    }
//                }else {
//                    Log.e("response","fail");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UnsetFavouriteResponse> call, Throwable throwable) {
//                Log.e("onfailure",throwable.toString());
//            }
//        });
//    }

    public void onPhoneDilogClick(View view) {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_lab_phoneno_dialog);

        adapter = new LabContactNumberAdapter(this);
        recyclerView = dialog.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dialog.show();

        tvcancel = dialog.findViewById(R.id.tv_cancel);
        tvok = dialog.findViewById(R.id.tv_ok);

        tvcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tvok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onContactNumberClick() {

    }
}

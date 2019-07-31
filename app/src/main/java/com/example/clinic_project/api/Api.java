package com.example.clinic_project.api;

import com.example.clinic_project.Activity.DoctorsByHospital;
import com.example.clinic_project.Response.BookResponse;
import com.example.clinic_project.Response.BookReturnResponse;
import com.example.clinic_project.Response.BookTakeResponse;
import com.example.clinic_project.Response.BuildingDetailResponse;
import com.example.clinic_project.Response.BuildingListResponse;
import com.example.clinic_project.Response.ClinicListResponse;
import com.example.clinic_project.Response.DoctorByHospitalResponse;
import com.example.clinic_project.Response.DoctorDetailResponse;
import com.example.clinic_project.Response.DoctorListResponse;
import com.example.clinic_project.Response.DoctorsByClinicResponse;
import com.example.clinic_project.Response.LoginResponse;
import com.example.clinic_project.Response.MyBookingResponse;
import com.example.clinic_project.Response.MyFavouriteDoctorResponse;
import com.example.clinic_project.Response.RegisterResponse;
import com.example.clinic_project.Response.SpecializationListResponse;
import com.example.clinic_project.Response.TownListResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginResponse>  login(@Field("phoneNumber") long PhoneNumber, @Field("password") String PassWord);

    @FormUrlEncoded
    @POST("/api/register")
    Call<RegisterResponse> userRegister(@Field("phoneNumber") long phoneNumber,
                                        @Field("password") String password,

                                        @Field("password_confirmation") String confirmPassword);

    @FormUrlEncoded
    @POST("/api/doctor_list")
    Call<DoctorListResponse> getDoctorList(@Field("token") String token);

    @FormUrlEncoded
    @POST("/api/doctor_list_by_specialization")
    Call<DoctorListResponse> getDoctorList(@Field("token") String token , @Field("specialization_id") int id);

    @FormUrlEncoded
    @POST("/api/specialization_list")
    Call<SpecializationListResponse> getSpecializationList(@Field("token") String token);

    @FormUrlEncoded
    @POST("/api/doctor_details")
    Call<DoctorDetailResponse> getDoctorDetail(@Field("token") String token, @Field("doctor_id") int id);

    @FormUrlEncoded
    @POST("/api/building_details")
    Call<BuildingDetailResponse> getBuildingDetail(@Field("token") String token,  @Field("type_id") int typeId, @Field("building_id") int buildingId);

    @FormUrlEncoded
    @POST("/api/building_list")
    Call<BuildingListResponse> getBuildingList(@Field("token") String token, @Field("type_id") int typeId, @Field("town_id") int townId);

    @FormUrlEncoded
    @POST("/api/town_list")
    Call<TownListResponse> getTownList(@Field("token") String token);

    @FormUrlEncoded
    @POST("/api/clinics_by_doctor")
    Call<ClinicListResponse> getClinicList(@Field("token") String token, @Field("doctor_id") int doctorId);

    @FormUrlEncoded
    @POST("/api/doctors_by_clinic")
    Call<DoctorsByClinicResponse> getDoctorByClinic(@Field("token") String token, @Field("clinic_id") int clinicId);

    @FormUrlEncoded
    @POST("/api/book")
    Call<BookTakeResponse> getBookTake(@Field("token") String token, @Field("date") String date, @Field("time_id") int timeId);

    @FormUrlEncoded
    @POST("/api/book_lists")
    Call<BookResponse> getBookList (@Field("token") String token, @Field("doctor_id") int doctorId, @Field("date") String date);

    @FormUrlEncoded
    @POST("/api/upcoming_booking")
    Call<MyBookingResponse> getMyBooking (@Field("token") String token);

    @FormUrlEncoded
    @POST("/api/favourite_doctor")
    Call<MyFavouriteDoctorResponse> getMyFavouriteDoctor (@Field("token") String token);

    @FormUrlEncoded
    @POST("/api/doctors_by_hospital")
    Call<DoctorByHospitalResponse> getDoctorsByHospital (@Field("token") String token, @Field("hospital_id") int hospitalId);

}




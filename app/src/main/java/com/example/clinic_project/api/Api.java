package com.example.clinic_project.api;

import com.example.clinic_project.Response.OtherServiceDetailResponse;
import com.example.clinic_project.Response.OtherServiceListResponse;
import com.example.clinic_project.Response.BookResponse;
import com.example.clinic_project.Response.BookTakeResponse;
import com.example.clinic_project.Response.BuildingDetailResponse;
import com.example.clinic_project.Response.BuildingListResponse;
import com.example.clinic_project.Response.ClinicListResponse;
import com.example.clinic_project.Response.DoctorByHospitalResponse;
import com.example.clinic_project.Response.DoctorDetailResponse;
import com.example.clinic_project.Response.DoctorListResponse;
import com.example.clinic_project.Response.DoctorsByClinicResponse;
import com.example.clinic_project.Response.FavouriteListResponse;
import com.example.clinic_project.Response.FavouriteResponse;
import com.example.clinic_project.Response.HospitalScheduleResponse;
import com.example.clinic_project.Response.LoginResponse;
import com.example.clinic_project.Response.MyBookingResponse;
import com.example.clinic_project.Response.MyFavouriteDoctorResponse;
import com.example.clinic_project.Response.RatingResponse;
import com.example.clinic_project.Response.RegisterResponse;
import com.example.clinic_project.Response.SpecializationListResponse;
import com.example.clinic_project.Response.TownListResponse;
import com.example.clinic_project.Response.UnsetFavouriteResponse;
import com.example.clinic_project.Response.UserRatingResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginResponse>  login(@Field("phone_number") String PhoneNumber, @Field("password") String password);

    @FormUrlEncoded
    @POST("/api/register")
    Call<RegisterResponse> userRegister(@Field("phone_number") String phoneNumber,
                                        @Field("password") String password,

                                        @Field("password_confirmation") String confirmPassword);

    @FormUrlEncoded
    @POST("/api/building_details")
    Call<BuildingDetailResponse> getBuildingDetail(@Field("token") String token, @Field("type") String type, @Field("building_id") int buildingId);

    @FormUrlEncoded
    @POST("/api/building_list")
    Call<BuildingListResponse> getBuildingList(@Field("token") String token, @Field("type") String type, @Field("town_id") int townId);

    @FormUrlEncoded
    @POST("/api/doctors_by_hospital")
    Call<DoctorByHospitalResponse> getDoctorsByHospital (@Field("token") String token, @Field("hospital_id") int hospitalId);

    @FormUrlEncoded
    @POST("/api/booking")
    Call<BookTakeResponse> getBookTake(@Field("token") String token, @Field("date") String date, @Field("schedule_id") int scheduleId);

    @FormUrlEncoded
    @POST("/api/hospital_schedule")
    Call<HospitalScheduleResponse> getHospitalSchedule (@Field("token") String token, @Field("hospital_id") int hospitalId, @Field("doctor_id") int doctorId, @Field("day") int day);

    @FormUrlEncoded
    @POST("/api/upcoming_bookings")
    Call<MyBookingResponse> getMyBooking (@Field("token") String token);

    @FormUrlEncoded
    @POST("/api/favourite")
    Call<FavouriteResponse> setFavourite (@Field("token") String token, @Field("favouriteable_id") int favouriteableId, @Field("favouriteable_type") String favouriteableType);

    @FormUrlEncoded
    @POST("/api/unset_favourite")
    Call<UnsetFavouriteResponse> unsetFavourite (@Field("token") String token, @Field("favouriteable_id") int favouriteableId, @Field("favouriteable_type") String favouriteableType);

    @FormUrlEncoded
    @POST("/api/favourite_list")
    Call<FavouriteListResponse> getFavouriteList (@Field("token") String token, @Field("favouriteable_type") String favouriteableType);

    @FormUrlEncoded
    @POST("/api/rating")
    Call<RatingResponse> getRating (@Field("token") String token, @Field("value") int value, @Field("rateable_id") int rateableId, @Field("rateable_type") String rateableType);

    @FormUrlEncoded
    @POST("/api/user_rating")
    Call<UserRatingResponse> getUserRating (@Field("token") String token, @Field("rateable_id") int rateableId, @Field("rateable_type") String rateableType);

    @FormUrlEncoded
    @POST("/api/other_service_list")
    Call<OtherServiceListResponse> getOtherServiceList (@Field("token") String token, @Field("type") String type, @Field("town_id") int townId);

    @FormUrlEncoded
    @POST("/api/other_service_details")
    Call<OtherServiceDetailResponse> getOtherServiceDetail (@Field("token") String token, @Field("type") String type, @Field("service_id") int serviceId);

    @FormUrlEncoded
    @POST("/api/doctor/all")
    Call<DoctorListResponse> getDoctorList(@Field("token") String token);

    @FormUrlEncoded
    @POST("/api/doctor_details")
    Call<DoctorDetailResponse> getDoctorDetail(@Field("token") String token, @Field("doctor_id") int id);




    @FormUrlEncoded
    @POST("/api/doctor_list_by_specialization")
    Call<DoctorListResponse> getDoctorList(@Field("token") String token , @Field("specialization_id") int id);

    @FormUrlEncoded
    @POST("/api/specialization_list")
    Call<SpecializationListResponse> getSpecializationList(@Field("token") String token);



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
    @POST("/api/book_lists")
    Call<BookResponse> getBookList (@Field("token") String token, @Field("doctor_id") int doctorId, @Field("date") String date);

    @FormUrlEncoded
    @POST("/api/favourite_doctor")
    Call<MyFavouriteDoctorResponse> getMyFavouriteDoctor (@Field("token") String token);


}




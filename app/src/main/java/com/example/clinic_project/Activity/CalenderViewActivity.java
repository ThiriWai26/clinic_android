package com.example.clinic_project.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.clinic_project.R;
import com.example.clinic_project.Response.BookResponse;
import com.example.clinic_project.Response.BookTakeResponse;
import com.example.clinic_project.adapter.BookAdapter;
import com.example.clinic_project.api.Api;
import com.example.clinic_project.holder.BookHolder;
import com.example.clinic_project.model.Booking;
import com.example.clinic_project.service.RetrofitService;
import com.example.clinic_project.service.Token;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalenderViewActivity extends AppCompatActivity implements BookHolder.OnItemClickListener {

    private CalendarView calendarView;
    private ImageView imgback;
    private Button btnBook;
    private RecyclerView recyclerView;
    private RetrofitService service;
    private BookAdapter adapter;
    private List<Booking> bookings = new ArrayList<>();
    private int doctorId = -1;
    private String date = null;
    private int timeId = -1;
    private String token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_view);

        initActivity();
    }

    private void initActivity() {

        calendarView = findViewById(R.id.calendarView);
        imgback = findViewById(R.id.imgback);
        btnBook = findViewById(R.id.btnbooking);
        recyclerView = findViewById(R.id.recyclerView);
        service = new RetrofitService();

        token= Token.MyToken.getToken();
        Log.e("bookActivity", "oncreate");

//        Bundle b = getIntent().getExtras();
//        token = b.getString("Token");

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        date = String.valueOf(day) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(year);
        Log.e("Date format",date);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                date = String.valueOf(dayOfMonth) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(year);
                Log.e("date", date);

            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("token", token);
                Api bookapi = service.getRetrofitService().create(Api.class);
                bookapi.getBookList(token,2,"24-7-2019").enqueue(new Callback<BookResponse>() {
                    @Override
                    public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().isSuccess) {
                                adapter.addItem(response.body().bookLists);

                                Log.e("BookLists", String.valueOf(bookings.size()));

                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<BookResponse> call, Throwable t) {

                    }
                });
            }
        });


        adapter = new BookAdapter(this);
        adapter.addItem(bookings);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClick(String date, int timeId) {

        this.date = date;
        this.timeId = timeId;

        Log.e("dayId,timeId", date + "," + String.valueOf(timeId));

    }
}

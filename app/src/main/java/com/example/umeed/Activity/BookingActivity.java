package com.example.umeed.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Adapter.BookingAdapter;
import com.example.umeed.Adapter.ViewReportws;
import com.example.umeed.DraggableFloatingActionButton;
import com.example.umeed.Models.BookingModel;
import com.example.umeed.Models.ReportsModel;
import com.example.umeed.databinding.ActivityBookingBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity{

    ActivityBookingBinding binding;

    FloatingActionButton call,message;
    DraggableFloatingActionButton sos;
    Animation fabOpen,fabClose;
    boolean isOpen=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvBooking.setLayoutManager(linearLayoutManager);

        rvBooking();

    }

    private void rvBooking() {

        SharedPreferences sharedPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        String aadhar = sharedPreferences.getString("aadharno","");

        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
        Call<BookingModel> call = apiInterface.userbookings(aadhar+"");
        call.enqueue(new Callback<BookingModel>() {
            @Override

            public void onResponse(Call<BookingModel> call, Response<BookingModel> response) {
                BookingModel model = response.body();
                if (!model.isError()){
                    if (model.getData()!= null){
                        BookingModel list = response.body();
                        Log.e("Doctor List Data", "onResponse: "+ list);
                        BookingAdapter adapter = new BookingAdapter(list.getData());
                        binding.rvBooking.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<BookingModel> call, Throwable t) {
                Log.e("Doctor List Data", "Error: "+ t);
            }
        });
    }

//    private void animateFab(){
//        if(isOpen){
//
//            call.startAnimation(fabClose);
//            message.startAnimation(fabClose);
//            call.setClickable(false);
//            message.setClickable(false);
//            isOpen=false;
//        }
//        else{
//            call.setX(sos.getX());
//            call.setY(sos.getY()-350);
//            message.setX(sos.getX());
//            message.setY(sos.getY()-200);
//
//            call.startAnimation(fabOpen);
//            message.startAnimation(fabOpen);
//            call.setClickable(true);
//            message.setClickable(true);
//            isOpen=true;
//        }
//    }


//    SharedPreferences sharedPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
//    String aadhar = sharedPreferences.getString("aadharno","");
//
//    ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
//    Call<ReportsModel> call = apiInterface.viewreportforbooking(aadhar+"");
//        call.enqueue(new Callback<ReportsModel>() {
//        @Override
//
//        public void onResponse(Call<ReportsModel> call, Response<ReportsModel> response) {
//            ReportsModel model = response.body();
//            if (!model.isError()){
//                if (model.getData()!= null){
//                    ReportsModel list = response.body();
//                    Log.e("Doctor List Data", "onResponse: "+ response.body());
//                    ViewReportws adapter = new ViewReportws(list.getData());
//                    binding.rvBooking.setAdapter(adapter);
//                }
//            }
//        }
//
//        @Override
//        public void onFailure(Call<ReportsModel> call, Throwable t) {
//            Log.e("Doctor List Data", "Error: "+ t);
//        }
//    });


}
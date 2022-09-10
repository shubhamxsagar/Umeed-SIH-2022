package com.example.umeed.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Adapter.BookingAdapter;
import com.example.umeed.Adapter.ReportsAdapter;
import com.example.umeed.Models.ReportsModel;
import com.example.umeed.R;
import com.example.umeed.databinding.ActivityBookingBinding;
import com.example.umeed.databinding.ActivityReportsListBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportsList extends AppCompatActivity {

    ActivityReportsListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportsListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvReports.setLayoutManager(linearLayoutManager);

        rvReports();

    }

    private void rvReports() {

        SharedPreferences sharedPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        String aadhar = sharedPreferences.getString("aadharno","");

        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
        Call<ReportsModel> call = apiInterface.userviewmedicalhistory(aadhar+"");
        call.enqueue(new Callback<ReportsModel>() {
            @Override

            public void onResponse(Call<ReportsModel> call, Response<ReportsModel> response) {
                ReportsModel model = response.body();
                if (!model.isError()){
                    if (model.getData()!= null){
                        ReportsModel list = response.body();
                        Log.e("Doctor List Data", "onResponse: "+ list);
                        ReportsAdapter adapter = new ReportsAdapter(list.getData());
                        binding.rvReports.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ReportsModel> call, Throwable t) {
                Log.e("Doctor List Data", "Error: "+ t);
            }
        });
    }
}
package com.example.umeed.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Adapter.BookingAdapter;
import com.example.umeed.Adapter.ComplaintsAdapter;
import com.example.umeed.DraggableFloatingActionButton;
import com.example.umeed.Models.BookingModel;
import com.example.umeed.Models.ComplaintsModel;
import com.example.umeed.R;
import com.example.umeed.databinding.ActivityBookingBinding;
import com.example.umeed.databinding.ActivityComplaintsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplaintsActivity extends AppCompatActivity {

    ActivityComplaintsBinding binding;

    FloatingActionButton call,message;
    DraggableFloatingActionButton sos;
    Animation fabOpen,fabClose;
    boolean isOpen=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityComplaintsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvComplaints.setLayoutManager(linearLayoutManager);

        rvComplaints();

    }

    private void rvComplaints() {

        SharedPreferences sharedPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        String aadhar = sharedPreferences.getString("aadharno","");

        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
        Call<ComplaintsModel> call = apiInterface.usercomplainlist(aadhar+"");
        call.enqueue(new Callback<ComplaintsModel>() {
            @Override

            public void onResponse(Call<ComplaintsModel> call, Response<ComplaintsModel> response) {
                ComplaintsModel model = response.body();
                if (!model.isError()){
                    if (model.getData()!= null){
                        ComplaintsModel list = response.body();
                        Log.e("Doctor List Data", "onResponse: "+ list);
                        ComplaintsAdapter adapter = new ComplaintsAdapter(list.getData());
                        binding.rvComplaints.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ComplaintsModel> call, Throwable t) {
                Log.e("Doctor List Data", "Error: "+ t);
            }
        });
    }
}
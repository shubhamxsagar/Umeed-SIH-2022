package com.example.umeed.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Adapter.BookingAdapter;
import com.example.umeed.Adapter.ReportsAdapter;
import com.example.umeed.Adapter.ViewReportws;
import com.example.umeed.Models.BookingModel;
import com.example.umeed.Models.ReportsModel;
import com.example.umeed.Models.StatusErrorModel;
import com.example.umeed.R;
import com.example.umeed.databinding.ActivityBookingBinding;
import com.example.umeed.databinding.ActivityBookingInfoBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingInfoActivity extends AppCompatActivity {

    ActivityBookingInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookingInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        binding.rvReports.setLayoutManager(linearLayoutManager);
        binding.rvBills.setLayoutManager(linearLayoutManager1);

        Intent intent = getIntent();
        String Organizationname = intent.getStringExtra("organizationname");
        String bookingid = intent.getStringExtra("bookingid");
        Log.e("Organizationname", "onCreate: "+Organizationname );
        binding.Organizationname.setText(Organizationname);

        rvReports();
        rvBills();

    }

    private void rvReports() {
        Intent intent = getIntent();
        String Organizationname = intent.getStringExtra("organizationname");
        String bookingid = intent.getStringExtra("bookingid");

        SharedPreferences sharedPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        String aadhar = sharedPreferences.getString("aadharno","");

        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
        Call<ReportsModel> call = apiInterface.viewreportforbooking(bookingid+"");
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

        binding.complaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(BookingInfoActivity.this, R.style.BookHospitalPopup);
                bottomSheetDialog.setContentView(R.layout.complaints_layout);
                bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
                //                FrameLayout bottomSheet = bottomSheetDialog.findViewById(R.id.frame_layout);
                //                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
                //                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                bottomSheetDialog.setCancelable(false);
                
                Button submit = bottomSheetDialog.findViewById(R.id.submit);
                ImageButton cancel = bottomSheetDialog.findViewById(R.id.cancelButton);
                TextInputEditText bookingDescription = bottomSheetDialog.findViewById(R.id.bookingDescription);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.dismiss();
                    }
                });

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = getIntent();
                        String Organizationname = intent.getStringExtra("organizationname");
                        String bookingid = intent.getStringExtra("bookingid");

                        SharedPreferences sharedPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
                        String aadhar = sharedPreferences.getString("aadharno","");

                        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
                        Call<StatusErrorModel> call = apiInterface.addbillcomplain(aadhar+"", bookingid+"",bookingDescription.getText()+"");
                        call.enqueue(new Callback<StatusErrorModel>() {
                            @Override

                            public void onResponse(Call<StatusErrorModel> call, Response<StatusErrorModel> response) {
                                StatusErrorModel model = response.body();
                                if (!model.isError()){
                                    bottomSheetDialog.dismiss();
                                    Toast.makeText(BookingInfoActivity.this, "Complaints successfully raised", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<StatusErrorModel> call, Throwable t) {
                                Log.e("Complaints", "Error: "+ t);
                            }
                        });
                    }
                });

                bottomSheetDialog.show();

            }
        });

                
                
                

              

    }
    private void rvBills() {
        Intent intent = getIntent();
        String Organizationname = intent.getStringExtra("organizationname");
        String bookingid = intent.getStringExtra("bookingid");

        SharedPreferences sharedPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        String aadhar = sharedPreferences.getString("aadharno","");

        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
        Call<ReportsModel> call = apiInterface.viewfinalbill(bookingid+"");
        call.enqueue(new Callback<ReportsModel>() {
            @Override

            public void onResponse(Call<ReportsModel> call, Response<ReportsModel> response) {
                ReportsModel model = response.body();
                if (!model.isError()){
                    if (model.getData()!= null){
                        ReportsModel list = response.body();
                        Log.e("Doctor List Data", "onResponse: "+ list);
                        ReportsAdapter adapter = new ReportsAdapter(list.getData());
                        binding.rvBills.setAdapter(adapter);
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
package com.example.umeed.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Models.StatusErrorModel;
import com.example.umeed.R;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AadharPage extends AppCompatActivity {

    TextInputEditText aadhar, otp;
    Button submit, verify_aadhar;
    LinearLayout linear_otp, linear_aadhar;

    TextView otpVerify, aadharVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadhar_page);

        otpVerify = findViewById(R.id.otpVerify);
        aadharVerify = findViewById(R.id.aadharVerify);
        aadhar = findViewById(R.id.aadhar_verify);
        submit = findViewById(R.id.aadhar_otp);
        linear_aadhar = findViewById(R.id.linear_aadhar);
        linear_otp = findViewById(R.id.linear_otp);
        otp = findViewById(R.id.enter_otp);
        verify_aadhar = findViewById(R.id.verify_aadhar);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
                Call<StatusErrorModel> call = apiInterface.checkifaadharisnotregister(aadhar.getText() + "");
                call.enqueue(new Callback<StatusErrorModel>() {
                    @Override
                    public void onResponse(Call<StatusErrorModel> call, Response<StatusErrorModel> response) {

                        StatusErrorModel statusErrorModel = response.body();
                        if (!statusErrorModel.isError()) {

                            ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
                            Call<StatusErrorModel> call2 = apiInterface.sendotpforaadharverification(aadhar.getText()+"");
                            call2.enqueue(new Callback<StatusErrorModel>() {
                                @Override
                                public void onResponse(Call<StatusErrorModel> call, Response<StatusErrorModel> response) {

                                    if (!response.body().isError()){
                                        linear_aadhar.setVisibility(View.GONE);
                                        linear_otp.setVisibility(View.VISIBLE);
                                        aadharVerify.setVisibility(View.GONE);
                                        otpVerify.setVisibility(View.VISIBLE);

                                        verify_aadhar.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                ApiInterface apiInterface1 = Controller.getRetrofit().create(ApiInterface.class);
                                                Call<StatusErrorModel> call1 = apiInterface1.verfiyotp(aadhar.getText() + "", otp.getText() + "");
                                                call1.enqueue(new Callback<StatusErrorModel>() {
                                                    @Override
                                                    public void onResponse(Call<StatusErrorModel> call, Response<StatusErrorModel> response) {

                                                        if (!response.body().isError()){
                                                            Intent intent = new Intent(AadharPage.this, SignUp.class);
                                                            intent.putExtra("aadhar", aadhar.getText().toString());
                                                            startActivity(intent);
                                                            finish();

                                                        }
                                                        Toast.makeText(AadharPage.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                                                    }

                                                    @Override
                                                    public void onFailure(Call<StatusErrorModel> call, Throwable t) {

                                                    }
                                                });
                                            }
                                        });
                                    }
                                    Toast.makeText(AadharPage.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<StatusErrorModel> call, Throwable t) {

                                }
                            });



                        }
                        Toast.makeText(AadharPage.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<StatusErrorModel> call, Throwable t) {
                        Log.e("Aadhar Register Verify", "onFailure: " + t);
                    }
                });
            }
        });


    }
}
package com.example.umeed.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Chat.ChatInfo;
import com.example.umeed.DraggableFloatingActionButton;
import com.example.umeed.Models.LoginDataModel;
import com.example.umeed.Models.LoginModel;
import com.example.umeed.Map.MapModel;
import com.example.umeed.Models.ProfileDataModel;
import com.example.umeed.Models.ProfileModel;
import com.example.umeed.Models.StatusErrorModel;
import com.example.umeed.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    DraggableFloatingActionButton sos;
    Animation fabOpen,fabClose;
    boolean isOpen=false;
    FloatingActionButton call;

    ProgressDialog progressDialog;
//    LinearLayout LinearPassword, LinearAadhar;
    TextInputEditText aadhar_txt, password_txt;
    Button  loginbutton2, login, SignUP;
    TextView loginwithotp;
    Button guest;

    ArrayList<LoginModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        progressDialog = new ProgressDialog(this);
//        progressDialog.setTitle("Checking Aadhar Please Wait");
//        progressDialog.show();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        //        loginbutton2=(Button) findViewById(R.id.loginbutton2);
//        loginotp=(EditText) findViewById(R.id.loginotp);

        SignUP = findViewById(R.id.signUP);
        password_txt = findViewById(R.id.password_txt);
        login = findViewById(R.id.login);
        aadhar_txt = findViewById(R.id.aadhar_txt);
//        loginwithpassword = findViewById(R.id.loginwithpassword);
        loginwithotp = findViewById(R.id.loginwithotp);
        guest = findViewById(R.id.GuestLogin);
//        LinearPassword = findViewById(R.id.pass_field);
//        LinearAadhar = findViewById(R.id.aadhar_crt);





        call = findViewById(R.id.call_button);
        sos = findViewById(R.id.sos_button);

        //Animations
        fabOpen = AnimationUtils.loadAnimation(this,R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this,R.anim.fab_close);

        //OnClick on sos button
        sos.setCustomClickListener(new DraggableFloatingActionButton.CustomClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
            }
        });

        Intent intent = getIntent();
        String lat = intent.getStringExtra("CLatitude");
        String lng =intent.getStringExtra("CLongitude");
        String aad =intent.getStringExtra("aadharPass");


        //OnClick on Call button
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:112"));
                startActivity(intent);


                animateFab();
            }
        });

        //OnClick on Message button






        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, HomePage.class));
            }
        });


        SignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, AadharPage.class));
            }
        });

        loginwithotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("Aadhar", "onResponse:    " +aadhar_txt.getText().toString());

                ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
                Call<StatusErrorModel> call3 = apiInterface.checkifaadharisregister(aadhar_txt.getText() + "");
                call3.enqueue(new Callback<StatusErrorModel>() {
                    @Override
                    public void onResponse(Call<StatusErrorModel> call3, Response<StatusErrorModel> response) {

                        StatusErrorModel checkaadhar = response.body();
                        if (!checkaadhar.isError()){

                            Log.e("Aadhar Verification", "onResponse Verify Aadhar:    " +response);
                            ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
                            Call<StatusErrorModel> call2 = apiInterface.sendotp(aadhar_txt.getText()+"");
                            call2.enqueue(new Callback<StatusErrorModel>() {
                                @Override
                                public void onResponse(Call<StatusErrorModel> call2, Response<StatusErrorModel> response) {

                                    StatusErrorModel statusErrorModel = response.body();
                                    if (!statusErrorModel.isError()){

                                        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Login.this, R.style.DialogStyle);
                                        bottomSheetDialog.setContentView(R.layout.bottom_login_otp_layout);

//                                        bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
                                        //                FrameLayout bottomSheet = bottomSheetDialog.findViewById(R.id.frame_layout);
                                        //                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
                                        //                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                                        bottomSheetDialog.setCancelable(false);

                                        Button submit = bottomSheetDialog.findViewById(R.id.submit);
                                        ImageButton cancel = bottomSheetDialog.findViewById(R.id.cancelButton);
                                        TextInputEditText otp = bottomSheetDialog.findViewById(R.id.otp);

                                        cancel.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                bottomSheetDialog.dismiss();
                                            }
                                        });

                                        submit.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                ApiInterface apiInterface1 = Controller.getRetrofit().create(ApiInterface.class);
                                                Call<StatusErrorModel> call1 = apiInterface1.verfiyotp(aadhar_txt.getText() + "", otp.getText() + "");
                                                call1.enqueue(new Callback<StatusErrorModel>() {
                                                    @Override
                                                    public void onResponse(Call<StatusErrorModel> call1, Response<StatusErrorModel> response) {

                                                        SharedPreferences sharedPreferences = getSharedPreferences("credentials", MODE_PRIVATE);
                                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                                        editor.putString("aadharno", aadhar_txt.getText().toString());
                                                        editor.apply();

                                                        Intent intent = new Intent(v.getContext(), HomePage.class);
                                                        startActivity(intent);
                                                        bottomSheetDialog.dismiss();
                                                        finish();

                                                    }

                                                    @Override
                                                    public void onFailure(Call<StatusErrorModel> call1, Throwable t) {

                                                    }
                                                });
                                            }
                                        });

                                        bottomSheetDialog.show();

                                    }
                                    Toast.makeText(Login.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                                }


                                @Override
                                public void onFailure(Call<StatusErrorModel> call2, Throwable t) {
                                    Log.e("Login OTP Fail", "onFailure: OTP" + t);
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<StatusErrorModel> call3, Throwable t) {
                        Log.e("Aadhar Verification", "onResponse Verify Aadhar:    " +t);
                    }
                });
            }
        });


        login.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                            LinearAadhar.setVisibility(View.GONE);
//                            LinearPassword.setVisibility(View.VISIBLE);

//                            login.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {

                                    ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
                                    Call<LoginDataModel> call1 = apiInterface.login(aadhar_txt.getText()+"", password_txt.getText()+"");
                                    call1.enqueue(new Callback<LoginDataModel>() {
                                        @Override
                                        public void onResponse(Call<LoginDataModel> call, Response<LoginDataModel> response) {
                                            Log.e("Only Login", "Success: Only Login"+response.body().isError() );

                                            LoginDataModel loginModel = response.body();

                                            if (!loginModel.isError()){
                                                    if (loginModel.getData()!=null){
                                                        SharedPreferences sharedPreferences = getSharedPreferences("credentials", MODE_PRIVATE);
                                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                                        editor.putString("aadharno", aadhar_txt.getText().toString());
                                                        editor.putString("passwordno", password_txt.getText().toString());
                                                        editor.apply();

                                                        Intent intent = new Intent(Login.this, HomePage.class);
                                                        startActivity(intent);
                                                        finish();

                                                    }

                                            }
                                            Toast.makeText(Login.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onFailure(Call<LoginDataModel> call, Throwable t) {
                                            Log.e("Only Login", "onFailure: Only Login"+t );
                                        }
                                    });
//
//                                }
//                            });
                    }
        }));



//        loginbutton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Login.this,HomePage.class);
//                startActivity(intent);
//            }
//        });

    }

    private void animateFab(){
        if(isOpen){

            call.startAnimation(fabClose);
            call.setClickable(false);
            isOpen=false;
        }
        else{
            call.setX(sos.getX());
            call.setY(sos.getY()-100);

            call.startAnimation(fabOpen);
            call.setClickable(true);
            isOpen=true;
        }
    }

}
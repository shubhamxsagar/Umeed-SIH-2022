package com.example.umeed.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Chat.ChatInfo;
import com.example.umeed.Chat.ContactChatFragment;
import com.example.umeed.DraggableFloatingActionButton;
import com.example.umeed.Fragment.AccountFragment;
import com.example.umeed.Fragment.HomeFragment;
import com.example.umeed.Map.MapsFragment;
import com.example.umeed.Models.ProfileDataModel;
import com.example.umeed.Models.ProfileModel;
import com.example.umeed.Models.StatusErrorModel;
import com.example.umeed.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView, guestBottomMenu;
    FloatingActionButton call,message;
    DraggableFloatingActionButton sos;
    Animation fabOpen,fabClose;
    boolean isOpen=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        guestBottomMenu = findViewById(R.id.bottomNavigationViewGuest);
        guestBottomMenu.setItemIconTintList(null);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        replaceFragment(new HomeFragment());
        bottomNavigationView.setItemIconTintList(null);

        call = findViewById(R.id.call_button);
        message = findViewById(R.id.message_button);
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

        String location = "https://www.google.com/maps?q=" + lat +","+lng+"&z=17&hl=en";

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
        SharedPreferences sharedPreferences = getSharedPreferences("credentials", MODE_PRIVATE);
        if (sharedPreferences.contains("aadharno")) {
            message.setVisibility(View.VISIBLE);
        }else{
            message.setVisibility(View.GONE);
        }

            //OnClick on Message button
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String smsNumber = "916206392765"; // E164 format without '+' sign
//                Intent sendIntent = new Intent(Intent.ACTION_SEND);
//                //  Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
//                sendIntent.setType("text/plain");
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "test \n");
//                sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
//                sendIntent.setPackage("com.whatsapp");
//                startActivity(sendIntent);
                animateFab();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(HomePage.this);
                builder1.setMessage("Are you sure you want to show the details with the emergency department?");

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                SharedPreferences sharedPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
                                String aadhar = sharedPreferences.getString("aadharno","");



                                ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
                                Call<ProfileModel> call = apiInterface.userdetails(aadhar+"");
                                call.enqueue(new Callback<ProfileModel>() {
                                    @Override
                                    public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                                        ProfileModel profileModel = response.body();
                                        Log.e("SMS", "onResponse: "+profileModel );

                                        if (!profileModel.isError()){
                                            ProfileDataModel model = response.body().getData();

                                            Log.e("aadhar", "onClick: "+aadhar);

                                            ApiInterface apiInterface1 = Controller.getRetrofit().create(ApiInterface.class);
                                            Call<StatusErrorModel> call1 = apiInterface1.sendsmstoemergency(aadhar+"",location+"");
                                            call1.enqueue(new Callback<StatusErrorModel>() {
                                                @Override
                                                public void onResponse(Call<StatusErrorModel> call, Response<StatusErrorModel> response) {
                                                    StatusErrorModel statusErrorModel = response.body();
                                                    Log.e("SMS", "onResponse: "+statusErrorModel );
                                                    if (!statusErrorModel.isError()){

                                                        String firstname = model.getFirstname();
                                                        String lastname = model.getLastname();
                                                        String mobile = model.getMobileno();
                                                        String state = model.getState();
                                                        String city = model.getCity();
                                                        String emergency1 = model.getEmergencycontact1();
                                                        String emergency2 = model.getEmergencycontact2();

                                                        Intent intent1 = new Intent(HomePage.this, ChatInfo.class);
                                                        intent1.putExtra("receiver", "emergencydepartment");
                                                        intent1.putExtra("firstname", firstname);
                                                        intent1.putExtra("lastname", lastname);
                                                        intent1.putExtra("mobile", mobile);
                                                        intent1.putExtra("state", state);
                                                        intent1.putExtra("city", city);
                                                        intent1.putExtra("lat", lat);
                                                        intent1.putExtra("lng", lng);
                                                        intent1.putExtra("emergency1", emergency1);
                                                        intent1.putExtra("emergency2", emergency2);
                                                        startActivity(intent1);
                                                        dialogInterface.cancel();
                                                    }

                                                    Toast.makeText(HomePage.this, statusErrorModel.getStatus(), Toast.LENGTH_SHORT).show();
                                                }

                                                @Override
                                                public void onFailure(Call<StatusErrorModel> call, Throwable t) {
                                                    Log.e("SMS", "onError: "+t );
                                                }
                                            });


                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ProfileModel> call, Throwable t) {
                                    }
                                });

                            }
                        }
                );
                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }
                );
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        SharedPreferences sharedPreferences2 = getSharedPreferences("credentials", MODE_PRIVATE);
        if (sharedPreferences2.contains("aadharno")) {

            bottomNavigationView.setVisibility(View.VISIBLE);
            bottomNavigationView.setOnItemSelectedListener(item -> {

                switch (item.getItemId()){

                    case R.id.home:
                        replaceFragment(new HomeFragment());
                        break;

                    case R.id.location:
                        replaceFragment(new MapsFragment());
//                    Intent intent = new Intent(this, MapsFragment.class);
//                    startActivity(intent);
//                    bottomNavigationView.getMenu().findItem(R.id.home).isChecked();
                        break;
                    case R.id.chat:
                        replaceFragment(new ContactChatFragment());
                        break;
                    case R.id.accounts:
                        replaceFragment(new AccountFragment());
                        break;
                }
                return true;
            });

        } else {
            guestBottomMenu.setVisibility(View.VISIBLE);
            guestBottomMenu.setOnItemSelectedListener(item -> {

                switch (item.getItemId()){

                    case R.id.home:
                        replaceFragment(new HomeFragment());
                        break;

                    case R.id.location:
                        replaceFragment(new MapsFragment());
//                    Intent intent = new Intent(this, MapsFragment.class);
//                    startActivity(intent);
//                    bottomNavigationView.getMenu().findItem(R.id.home).isChecked();
                        break;
                }
                return true;
            });
        }
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void animateFab(){
        if(isOpen){

            call.startAnimation(fabClose);
            message.startAnimation(fabClose);
            call.setClickable(false);
            message.setClickable(false);
            isOpen=false;
        }
        else{
            call.setX(sos.getX());
            call.setY(sos.getY()-200);
            message.setX(sos.getX());
            message.setY(sos.getY()-350);

            call.startAnimation(fabOpen);
            message.startAnimation(fabOpen);
            call.setClickable(true);
            message.setClickable(true);
            isOpen=true;
        }
    }
}
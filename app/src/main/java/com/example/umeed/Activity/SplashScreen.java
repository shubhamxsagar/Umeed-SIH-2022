package com.example.umeed.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Models.AllListModel;
import com.example.umeed.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {

    Animation fade;
    ImageView imageView;
    Handler handler;
    String lat, lng;
    Location location;
    LocationManager locationManage;
    Context mContext;
    LocationRequest locationRequest;
    private static final int Request_code = 101;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mContext = this;
        imageView = findViewById(R.id.imageview);
        fade = AnimationUtils.loadAnimation(this,R.anim.fade_in);

        imageView.setAnimation(fade);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(SplashScreen.this, Manifest.permission.ACCESS_FINE_LOCATION)
                    && ActivityCompat.shouldShowRequestPermissionRationale(SplashScreen.this, Manifest.permission.ACCESS_FINE_LOCATION )){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_code);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, Request_code);

            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_code);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, Request_code);

            }

            return;
        }

        locationRequest = LocationRequest.create();
        locationRequest.setInterval(400);
        locationRequest.setFastestInterval(2000);
        locationRequest.setPriority(Priority.PRIORITY_HIGH_ACCURACY);
        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationAvailability(@NonNull LocationAvailability locationAvailability) {
                super.onLocationAvailability(locationAvailability);
            }
        };

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                Log.e("", "onSuccess: "+location );
                if (location == null){
                    if (ContextCompat.checkSelfPermission(
                            SplashScreen.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(
                            SplashScreen.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        if (ActivityCompat.shouldShowRequestPermissionRationale(SplashScreen.this, Manifest.permission.ACCESS_FINE_LOCATION)
                                && ActivityCompat.shouldShowRequestPermissionRationale(SplashScreen.this, Manifest.permission.ACCESS_FINE_LOCATION )){
                            ActivityCompat.requestPermissions(SplashScreen.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_code);
                            ActivityCompat.requestPermissions(SplashScreen.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, Request_code);

                        }else{
                            ActivityCompat.requestPermissions(SplashScreen.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_code);
                            ActivityCompat.requestPermissions(SplashScreen.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, Request_code);

                        }

                        return;
                    }
                }

                if (location != null) {

//                    progressBar.setVisibility(View.GONE);
                    lat = String.valueOf(location.getLatitude());
                    lng = String.valueOf(location.getLongitude());

                    Log.e("Latitude", "onSuccess: "+lat );
                    Log.e("Latitude", "onSuccess: "+lng );

                    handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (ActivityCompat.checkSelfPermission(
                                    SplashScreen.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                                    && ActivityCompat.checkSelfPermission(
                                    SplashScreen.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                                ActivityCompat.requestPermissions(SplashScreen.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_code);

                                return;
                            }

                            SharedPreferences sharedPreferences = getSharedPreferences("credentials", MODE_PRIVATE);
                            SharedPreferences sharedPreferences2 = getSharedPreferences("credentialsOTP", MODE_PRIVATE);
                            if (sharedPreferences.contains("aadharno")) {

//                                locationManage = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
//                                if (ActivityCompat.checkSelfPermission(SplashScreen.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SplashScreen.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                                    // TODO: Consider calling
//                                    //    ActivityCompat#requestPermissions
//                                    // here to request the missing permissions, and then overriding
//                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                                    //                                          int[] grantResults)
//                                    // to handle the case where the user grants the permission. See the documentation
//                                    // for ActivityCompat#requestPermissions for more details.
//                                    return;
//                                }
//                                locationManage.requestLocationUpdates(LocationManager.GPS_PROVIDER,
//                                        2000,
//                                        10, locationListenerGPS);

                                Intent intent = new Intent(SplashScreen.this, HomePage.class);
                                String aadhar = sharedPreferences.getString("aadharno", "");
                                intent.putExtra("aadharPass", aadhar);
                                intent.putExtra("CLatitude",lat);
                                intent.putExtra("CLongitude",lng);

                                Log.e("LatLng Hospital", "rvHospital: "+lat );
                                Log.e("LatLng Hospital", "rvHospital: "+lng );


                                startActivity(intent);
                                finish();

//                } else if (sharedPreferences2.contains("aadharnoOTP")) {
//
//                    Intent intent = new Intent(SplashScreen.this, HomePage.class);
//                    String aadhar = sharedPreferences2.getString("aadharnoOTP", "");
//                    intent.putExtra("aadharPass", aadhar);
//                    startActivity(intent);
//                    finish();

                            } else {
                                SharedPreferences SkipLogin = getSharedPreferences("SkipLogin", MODE_PRIVATE);

                                if (SkipLogin.contains("skip")){
                                    Intent intent = new Intent(SplashScreen.this, Login.class);
                                    intent.putExtra("CLatitude",lat);
                                    intent.putExtra("CLongitude",lng);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Intent intent = new Intent(SplashScreen.this, OnboardingActivity.class);
                                    intent.putExtra("CLatitude",lat);
                                    intent.putExtra("CLongitude",lng);
                                    startActivity(intent);
                                    finish();
                                }

                            }
                        }
                    }, 1000);





                }

            }
        });




    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(SplashScreen.this,
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    }
                } else {
                }
                return;
            }
        }
    }
}
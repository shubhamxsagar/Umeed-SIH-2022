package com.example.umeed.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.umeed.R;
import com.example.umeed.databinding.ActivityDoctorListBinding;
import com.example.umeed.databinding.ActivityMapBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapActivity extends AppCompatActivity implements GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback {

    ActivityMapBinding binding;
    Button mLocation;
//    ProgressBar progressBar;

    ProgressBar progressBar;
    Context context;
    ProgressDialog progressDialog;
    GoogleMap mMap;
    Location currentLocation;
    private static final int Request_code = 101;
    private FusedLocationProviderClient fusedLocationProviderClient;
    double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getCurrentLocation();


        Intent intent = getIntent();
        String lat = intent.getStringExtra("CLatitude");
        String lng = intent.getStringExtra("CLongitude");
    }

    private void getCurrentLocation() {


//        progressBar = rootView.findViewById(R.id.progress_bar);
//        mLocation = findViewById(R.id.my_location);

//        progressBar.setVisibility(View.VISIBLE);

        if (ActivityCompat.checkSelfPermission(
                MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                MapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_code);

            return;
        }

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(100);
        locationRequest.setPriority(Priority.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(3000);
        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {

//                Toast.makeText(getApplicationContext(), "location result is =" + locationResult, Toast.LENGTH_SHORT).show();

                if (locationResult == null) {
//                    Toast.makeText(getApplicationContext(), "Current location is null", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (Location location : locationResult.getLocations()) {
//                    Toast.makeText(getApplicationContext(), "Current location is"+ location.getLongitude(), Toast.LENGTH_SHORT).show();

                }
            }
        };

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {

//                    progressBar.setVisibility(View.GONE);
                    currentLocation = location;
                    lat = location.getLatitude();
                    lng = location.getLongitude();

                    Log.e("Latitude", "onSuccess: " + lat);
                    Log.e("Latitude", "onSuccess: " + lng);

                    LatLng latLng = new LatLng(lat, lng);
//                    mMap.addMarker(new MarkerOptions().position(latLng).title("current location"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    if (ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }

                    mMap.setMyLocationEnabled(true);
//                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

//
                    String snippet = "Address";

                    Intent intent = getIntent();
                    String latM = intent.getStringExtra("lattitude");
                    String lngM =intent.getStringExtra("longitude");

                    Log.e("Map", "onSuccess: "+latM );
                    Log.e("Map", "onSuccess: "+lngM );

//                    LatLng SingleMap = new LatLng(Double.parseDouble(latM), Double.parseDouble(lngM));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(latM), Double.parseDouble(lngM)),14.0f));
                    mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(latM), Double.parseDouble(lngM))));
//                    for (int i=0; i<locationArrayList.size(); i++){
//
//                        for (int j=0; j<title.size(); j++){
//
//                            mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title(String.valueOf(title.get(j))).snippet(snippet)).showInfoWindow();
//                            mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));
//
//                        }
//                        mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
//
//                    }
                }
            }
        });

    }

    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

//        LatLng sydney new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude())

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        getCurrentLocation();
    }
}
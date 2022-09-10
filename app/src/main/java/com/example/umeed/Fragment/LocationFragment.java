package com.example.umeed.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.umeed.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class LocationFragment extends Fragment{

    private GoogleMap mMap;
    private static final int Request_code=101;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    double lat, lng;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.getApplicationContext());
//
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//
//
//        String url = "https:maps.google.com/maps/api/place/nearbysearch/json?"+
//                "location="+ lat+","+lng+
//                "&radius=5000"+
//                "&type=hospital"+
//                "&sensor=true"+
//                "&key" +getResources().getString(R.string.google_map_key);


    }



//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_location, container, false);
//    }
//
//    @Override
//    public void onMapReady(@NonNull GoogleMap googleMap) {
//
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
////        LatLng sydney = new LatLng(-34, 151);
////        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
////        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//
//        getCurrentLocation();
//
//    }
//
//    private void getCurrentLocation(){
//
//        if (ActivityCompat.checkSelfPermission(
//                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(
//                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_code);
//
//            return;
//        }
//
//        locationRequest = LocationRequest.create();
//        locationRequest.setInterval(6000);
//        locationRequest.setFastestInterval(5000);
//        LocationCallback locationCallback = new LocationCallback() {
//            @Override
//            public void onLocationResult(@NonNull LocationResult locationResult) {
//                Toast.makeText(getApplicationContext(), "location result is =" + locationResult, Toast.LENGTH_SHORT).show();
//
//                if (locationResult == null){
//                    Toast.makeText(getApplicationContext(), "Current location is null", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                for (Location location: locationResult.getLocations()){
//                    Toast.makeText(getApplicationContext(), "Current location is"+ location.getLongitude(), Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        };
//
//        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
//        Task<Location> task = fusedLocationProviderClient.getLastLocation();
//        task.addOnSuccessListener(new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//
//                if (location !=null){
//                    lat = location.getLatitude();
//                    lng = location.getLongitude();
//
//                    LatLng latLng = new LatLng(lat, lng);
//                    mMap.(new MarkerOptions().position(latLng).title("current location"));
//                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
//
//                }
//
//            }
//        });
//
//    }

}
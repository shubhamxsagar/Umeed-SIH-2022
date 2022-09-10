package com.example.umeed.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Activity.HomePage;
import com.example.umeed.Adapter.BloodListAdapter;
import com.example.umeed.Adapter.DoctorListAdapter;
import com.example.umeed.Adapter.HospitalListAdapter;
import com.example.umeed.Adapter.LabListAdapter;
import com.example.umeed.Adapter.MapListAdapter;
import com.example.umeed.Map.BloodHospitalInfoWindowAdapter;
import com.example.umeed.Map.DoctorInfoWindowAdapter;
import com.example.umeed.Map.HospitalInfoWindowAdapter;
import com.example.umeed.Map.LabInfoWindowAdapter;
import com.example.umeed.Map.MapModel;
import com.example.umeed.Map.RvBloodAdapter;
import com.example.umeed.Map.RvDoctorAdapter;
import com.example.umeed.Map.RvHospitalAdapter;
import com.example.umeed.Map.RvLabAdapter;
import com.example.umeed.Models.AllListDataModel;
import com.example.umeed.Models.AllListModel;
import com.example.umeed.R;
import com.example.umeed.databinding.FragmentHospitalMapViewBinding;
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
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalMapView extends Fragment implements GoogleMap.OnInfoWindowClickListener,OnMapReadyCallback{

    FragmentHospitalMapViewBinding binding;
    Button mLocation;
//    ProgressBar progressBar;

    ProgressBar progressBar;
    Context context;
    ProgressDialog progressDialog;
    private GoogleMap mMap;
    Location currentLocation;
    private static final int Request_code = 101;
    private FusedLocationProviderClient fusedLocationProviderClient;
    double lat, lng;
    MapListAdapter.UserListRecyclerClickListener mClickListener;
    Chip ChipHospital, ChipDoctor, ChipLab, ChipBlood;

    MapListAdapter mapListAdapter;
    RecyclerView recyclerView;

    private ArrayList<AllListDataModel> list = new ArrayList<>();
    String URLString;

    private ArrayList<LatLng> locationArrayList;
    LatLng A = new LatLng(21.229380, 81.349051);
    LatLng B = new LatLng(21.235953, 81.352923);



    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        context = getActivity();

        // Inflate the layout for this fragment
        binding = binding.inflate(inflater, container, false);

//        recyclerView = rootView.findViewById(R.id.rvMap);
//        MapListAdapter adapter = new MapListAdapter(list, this);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);
        getCurrentLocation();


        Intent intent = getActivity().getIntent();
        String lat = intent.getStringExtra("CLatitude");
        String lng = intent.getStringExtra("CLongitude");

        return binding.getRoot();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        LatLng sydney new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude())

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        getCurrentLocation();
    }


    private void getCurrentLocation() {


//        progressBar = rootView.findViewById(R.id.progress_bar);
//        mLocation = findViewById(R.id.my_location);

//        progressBar.setVisibility(View.VISIBLE);

        if (ActivityCompat.checkSelfPermission(
                getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_code);

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
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

                    Intent intent = getActivity().getIntent();
                    String latM = intent.getStringExtra("lattitude");
                    String lngM =intent.getStringExtra("longitude");

//                    LatLng SingleMap = new LatLng(Double.parseDouble(latM), Double.parseDouble(lngM));
//                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(latM), Double.parseDouble(lngM)),14.0f));

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

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (Request_code){

            case Request_code:
                if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    getCurrentLocation();
                }
                break;
        }
    }


    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {

    }

}
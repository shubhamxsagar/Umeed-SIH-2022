package com.example.umeed.Map;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Activity.HomePage;
import com.example.umeed.Activity.Login;
import com.example.umeed.Adapter.BloodListAdapter;
import com.example.umeed.Adapter.DoctorListAdapter;
import com.example.umeed.Adapter.HospitalListAdapter;
import com.example.umeed.Adapter.LabListAdapter;
import com.example.umeed.Adapter.MapListAdapter;
import com.example.umeed.Models.AllListDataModel;
import com.example.umeed.Models.AllListModel;
import com.example.umeed.Models.StatusErrorModel;
import com.example.umeed.R;
import com.example.umeed.databinding.FragmentMapsBinding;
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
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsFragment extends Fragment implements GoogleMap.OnInfoWindowClickListener,OnMapReadyCallback
                                                        , MapListAdapter.UserListRecyclerClickListener{

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

    FragmentMapsBinding binding;

    MapListAdapter mapListAdapter;
    RecyclerView recyclerView;

    private ArrayList<AllListDataModel> list = new ArrayList<>();
    String URLString;

    private ArrayList<LatLng> locationArrayList;
    LatLng A = new LatLng(21.229380, 81.349051);
    LatLng B = new LatLng(21.235953, 81.352923);

    ArrayList<String> title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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

        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.rvMap.setLayoutManager(HorizontalLayout);

        Intent intent = getActivity().getIntent();
        String lat = intent.getStringExtra("CLatitude");
        String lng = intent.getStringExtra("CLongitude");




        return binding.getRoot();
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = FragmentMapsBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
//        mapFragment.getMapAsync(this);
//        getCurrentLocation();
//
////        locationArrayList = new ArrayList<>();
////        locationArrayList.add(A);
////        locationArrayList.add(B);
////
////        title = new ArrayList<>();
////        title.add("A");
////        title.add("B");
//
//        String url = "https:maps.google.com/maps/api/place/nearbysearch/json?" +
//                "location=" + lat + "," + lng +
//                "&radius=5000" +
//                "&type=hospital" +
//                "&sensor=true" +
//                "&key" + getResources().getString(R.string.google_map_key);
//
//
//    }

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

                    Log.e("Latitude", "onSuccess: "+lat );
                    Log.e("Latitude", "onSuccess: "+lng );

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
                    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(@NonNull Marker marker) {
                            String markerTitle = marker.getTitle();

                            Intent intent = new Intent(getActivity(), HomePage.class);
                            intent.putExtra("title", markerTitle);
//                            startActivity(intent);

                            return false;
                        }
                    });
                    binding.CardList.setVisibility(View.VISIBLE);



                    binding.CardList.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.DialogStyle);
                            bottomSheetDialog.setContentView(R.layout.bottom_hospital_list_layout);

                            bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
                            //                FrameLayout bottomSheet = bottomSheetDialog.findViewById(R.id.frame_layout);
                            //                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
                            //                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                            RecyclerView hospital = bottomSheetDialog.findViewById(R.id.AllListHospital);
                            RecyclerView blood = bottomSheetDialog.findViewById(R.id.AllListBlood);
                            RecyclerView lab = bottomSheetDialog.findViewById(R.id.AllListLab);
                            RecyclerView doctor = bottomSheetDialog.findViewById(R.id.AllListDoctor);

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                            hospital.setLayoutManager(linearLayoutManager);

                            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity());
                            blood.setLayoutManager(linearLayoutManager2);

                            LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getActivity());
                            lab.setLayoutManager(linearLayoutManager3);

                            LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(getActivity());
                            doctor.setLayoutManager(linearLayoutManager4);

                            ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
                            Call<AllListModel> call = apiInterface.listofhospital(lat+"",lng+"");
                            call.enqueue(new Callback<AllListModel>() {
                                @Override
                                public void onResponse(Call<AllListModel> call, Response<AllListModel> response) {
                                    AllListModel model = response.body();
                                    if (!model.isError()){
                                        if (model.getData()!= null){
                                            AllListModel list = response.body();
                                            Log.e("Doctor List Data", "onResponse: "+ list);
                                            HospitalListAdapter adapter = new HospitalListAdapter(list.getData());
                                            hospital.setAdapter(adapter);
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<AllListModel> call, Throwable t) {
                                    Log.e("Doctor List Data", "Error: "+ t);
                                }
                            });

                            Call<AllListModel> call2 = apiInterface.listofppd(lat+"",lng+"");
                            call2.enqueue(new Callback<AllListModel>() {
                                @Override
                                public void onResponse(Call<AllListModel> call, Response<AllListModel> response) {
                                    AllListModel model = response.body();
                                    if (!model.isError()){
                                        if (model.getData()!= null){
                                            AllListModel list = response.body();
                                            Log.e("Doctor List Data", "onResponse: "+ list);
                                            DoctorListAdapter adapter = new DoctorListAdapter(list.getData());
                                            doctor.setAdapter(adapter);
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<AllListModel> call, Throwable t) {
                                    Log.e("Doctor List Data", "Error: "+ t);
                                }
                            });


                            Call<AllListModel> call3 = apiInterface.listoflab(lat+"",lng+"");
                            call3.enqueue(new Callback<AllListModel>() {
                                @Override
                                public void onResponse(Call<AllListModel> call, Response<AllListModel> response) {
                                    AllListModel model = response.body();
                                    if (!model.isError()){
                                        if (model.getData()!= null){
                                            AllListModel list = response.body();
                                            Log.e("Doctor List Data", "onResponse: "+ list);
                                            LabListAdapter adapter = new LabListAdapter(list.getData());
                                            lab.setAdapter(adapter);
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<AllListModel> call, Throwable t) {
                                    Log.e("Doctor List Data", "Error: "+ t);
                                }
                            });

                            Call<AllListModel> call4 = apiInterface.listofbloodbank(lat+"",lng+"");
                            call4.enqueue(new Callback<AllListModel>() {
                                @Override
                                public void onResponse(Call<AllListModel> call, Response<AllListModel> response) {
                                    AllListModel model = response.body();
                                    if (!model.isError()){
                                        if (model.getData()!= null){
                                            AllListModel list = response.body();
                                            Log.e("Doctor List Data", "onResponse: "+ list);
                                            BloodListAdapter adapter = new BloodListAdapter(list.getData());
                                            blood.setAdapter(adapter);
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<AllListModel> call, Throwable t) {
                                    Log.e("Doctor List Data", "Error: "+ t);
                                }
                            });


                            bottomSheetDialog.show();
                        }
                    });


                    binding.ChipAll.setSelected(true);
//                    APICallHospital();
//                    APICallLab();
//                    APICallBlood();
//                    APICallDoctor();
                    APICallAll();

                    binding.ChipAll.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            binding.ChipAll.setSelected(true);
                            binding.CardList.setVisibility(View.VISIBLE);

                            mMap.clear();
                            binding.rvMap.setVisibility(View.GONE);

                            binding.ChipBlood.setTextColor(getActivity().getResources().getColor(R.color.black));
                            binding.ChipLab.setTextColor(getActivity().getResources().getColor(R.color.black));
                            binding.ChipDoctor.setTextColor(getActivity().getResources().getColor(R.color.black));
                            binding.ChipHospital.setTextColor(getActivity().getResources().getColor(R.color.black));

//                            APICallHospital();
//                            APICallLab();
//                            APICallBlood();
//                            APICallDoctor();
                            APICallAll();
                        }
                    });


                    binding.ChipHospital.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            binding.rvMap.setVisibility(View.VISIBLE);
                            binding.CardList.setVisibility(View.GONE);

                            mMap.clear();
                            if (binding.ChipHospital.isChecked()){
                                binding.ChipAll.setSelected(false);

                                binding.ChipHospital.setTextColor(getActivity().getResources().getColor(R.color.white));
                                binding.ChipBlood.setTextColor(getActivity().getResources().getColor(R.color.black));
                                binding.ChipLab.setTextColor(getActivity().getResources().getColor(R.color.black));
                                binding.ChipDoctor.setTextColor(getActivity().getResources().getColor(R.color.black));
                                binding.ChipAll.setTextColor(getActivity().getResources().getColor(R.color.black));

                                APICallHospital();

                            } else{
                                mMap.clear();
                            }
                        }
                    });

                    binding.ChipDoctor.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            binding.rvMap.setVisibility(View.VISIBLE);
                            binding.CardList.setVisibility(View.GONE);

                            if (binding.ChipDoctor.isChecked()){
                                binding.ChipAll.setSelected(false);

                                binding.ChipHospital.setTextColor(getActivity().getResources().getColor(R.color.black));
                                binding.ChipBlood.setTextColor(getActivity().getResources().getColor(R.color.black));
                                binding.ChipLab.setTextColor(getActivity().getResources().getColor(R.color.black));
                                binding.ChipDoctor.setTextColor(getActivity().getResources().getColor(R.color.white));
                                binding.ChipAll.setTextColor(getActivity().getResources().getColor(R.color.black));

                                mMap.clear();
                                APICallDoctor();

                            }else{
                                mMap.clear();
                            }
                        }
                    });

                    binding.ChipLab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            binding.rvMap.setVisibility(View.VISIBLE);
                            binding.CardList.setVisibility(View.GONE);

                            if (binding.ChipLab.isChecked()){
                                binding.ChipAll.setSelected(false);

                                binding.ChipHospital.setTextColor(getActivity().getResources().getColor(R.color.black));
                                binding.ChipBlood.setTextColor(getActivity().getResources().getColor(R.color.black));
                                binding.ChipLab.setTextColor(getActivity().getResources().getColor(R.color.white));
                                binding.ChipDoctor.setTextColor(getActivity().getResources().getColor(R.color.black));
                                binding.ChipAll.setTextColor(getActivity().getResources().getColor(R.color.black));

                                mMap.clear();
                                APICallLab();

                            }else{
                                mMap.clear();
                            }
                        }
                    });

                    binding.ChipBlood.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            binding.rvMap.setVisibility(View.VISIBLE);
                            binding.CardList.setVisibility(View.GONE);

                            if (binding.ChipBlood.isChecked()){
                                binding.ChipAll.setSelected(false);

                                binding.ChipHospital.setTextColor(getActivity().getResources().getColor(R.color.black));
                                binding.ChipBlood.setTextColor(getActivity().getResources().getColor(R.color.white));
                                binding.ChipLab.setTextColor(getActivity().getResources().getColor(R.color.black));
                                binding.ChipDoctor.setTextColor(getActivity().getResources().getColor(R.color.black));
                                binding.ChipAll.setTextColor(getActivity().getResources().getColor(R.color.black));

                                mMap.clear();
                                APICallBlood();
                            }else{
                                mMap.clear();
                            }
                        }
                    });


//                    ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
//                    apiInterface.mapList().enqueue(new Callback<AllListModel>() {
//                        @Override
//                        public void onResponse(Call<AllListModel> call, Response<AllListModel> response) {
//
//                            list = response.body().getData();
//                            initMarker(list);

//                            MapListAdapter adapter = new MapListAdapter(list, this::onUserClicked);
//                            recyclerView.setAdapter();

//                            Log.e("Adapter", "onResponse: "+recyclerView);

                        }

//                        private void onUserClicked(int i) {
//
//                            String selectedId = list.get(i).getOrganizationname();
//
//                            for (MapModel mapModel: list){
//                                if (selectedId.equals(mapModel.getCity())){
//                                    mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(Double.parseDouble(mapModel.getLatitude()), Double.parseDouble(mapModel.getLongitude()))), 600, null);
//                                    break;
//                                }
//                            }
//
//                        }

//                        @Override
//                        public void onFailure(Call<AllListModel> call, Throwable t) {
//
//                            Log.e("Failed Location", "onFailure:  Location"+t );
//
//                        }
//                    });


//                }

            }
        });

    }

    public void APICallAll(){
        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
        Call<AllListModel> call = apiInterface.listofhospital(lat+"",lng+"");
        call.enqueue(new Callback<AllListModel>() {
            @Override
            public void onResponse(Call<AllListModel> call, Response<AllListModel> response) {
                Log.e("Selected Hospital", "onClick: "+ response.body());
                AllListModel allListModel = response.body();
                if (!allListModel.isError()){
                    list = response.body().getData();

                    for (int i=0; i<list.size(); i++){

                        LatLng location = new LatLng(Double.parseDouble(list.get(i).getLattitude()),
                                Double.parseDouble(list.get(i).getLongitude()));

                        Marker marker = mMap.addMarker(new MarkerOptions().position(location)
                                .title(list.get(i).getOrganizationname()));
                        MapModel info = new MapModel();

                        int height = 115;
                        int width = 70;

                        Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.marker_hospital);
                        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                        BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);


                        LatLng latLng = new LatLng(Double.parseDouble(list.get(0).getLattitude()),
                                Double.parseDouble(list.get(0).getLongitude()));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng.latitude, latLng.longitude),12.0f));
                        mMap.addMarker(new MarkerOptions().position(location).icon(smallMarkerIcon));


                        if (list.size()!=0){
                            HospitalInfoWindowAdapter testInfoWindowAdapter = new HospitalInfoWindowAdapter(getActivity());
                            mMap.setInfoWindowAdapter(testInfoWindowAdapter);

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AllListModel> call, Throwable t) {

            }
        });

        Call<AllListModel> call2 = apiInterface.listoflab(lat+"",lng+"");
        call2.enqueue(new Callback<AllListModel>() {
            @Override
            public void onResponse(Call<AllListModel> call, Response<AllListModel> response) {
                Log.e("Selected Hospital", "onClick: "+ response.body());
                AllListModel allListModel = response.body();
                if (!allListModel.isError()){
                    list = response.body().getData();

                    for (int i=0; i<list.size(); i++){

                        LatLng location = new LatLng(Double.parseDouble(list.get(i).getLattitude()),
                                Double.parseDouble(list.get(i).getLongitude()));

                        Marker marker = mMap.addMarker(new MarkerOptions().position(location)
                                .title(list.get(i).getOrganizationname()));
                        MapModel info = new MapModel();

                        int height = 115;
                        int width = 70;

                        Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.marker_labs);
                        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                        BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);


                        LatLng latLng = new LatLng(Double.parseDouble(list.get(0).getLattitude()),
                                Double.parseDouble(list.get(0).getLongitude()));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng.latitude, latLng.longitude),12.0f));
                        mMap.addMarker(new MarkerOptions().position(location).icon(smallMarkerIcon));


                        if (list.size()!=0){
                            LabInfoWindowAdapter testInfoWindowAdapter = new LabInfoWindowAdapter(getActivity());
                            mMap.setInfoWindowAdapter(testInfoWindowAdapter);

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AllListModel> call, Throwable t) {

            }
        });


        Call<AllListModel> call3 = apiInterface.listofppd(lat+"",lng+"");
        call3.enqueue(new Callback<AllListModel>() {
            @Override
            public void onResponse(Call<AllListModel> call, Response<AllListModel> response) {
                Log.e("Selected Hospital", "onClick: "+ response.body());
                AllListModel allListModel = response.body();
                if (!allListModel.isError()){
                    list = response.body().getData();

                    for (int i=0; i<list.size(); i++){

                        LatLng location = new LatLng(Double.parseDouble(list.get(i).getLattitude()),
                                Double.parseDouble(list.get(i).getLongitude()));

                        Marker marker = mMap.addMarker(new MarkerOptions().position(location)
                                .title(list.get(i).getOrganizationname()));
                        MapModel info = new MapModel();

                        int height = 115;
                        int width = 70;

                        Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.marker_doctor);
                        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                        BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);


                        LatLng latLng = new LatLng(Double.parseDouble(list.get(0).getLattitude()),
                                Double.parseDouble(list.get(0).getLongitude()));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng.latitude, latLng.longitude),12.0f));
                        mMap.addMarker(new MarkerOptions().position(location).icon(smallMarkerIcon));


                        if (list.size()!=0){
                            DoctorInfoWindowAdapter testInfoWindowAdapter = new DoctorInfoWindowAdapter(getActivity());
                            mMap.setInfoWindowAdapter(testInfoWindowAdapter);

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AllListModel> call, Throwable t) {

            }
        });

        Call<AllListModel> call4 = apiInterface.listofbloodbank(lat+"",lng+"");
        call4.enqueue(new Callback<AllListModel>() {
            @Override
            public void onResponse(Call<AllListModel> call, Response<AllListModel> response) {
                Log.e("Selected Hospital", "onClick: "+ response.body());
                AllListModel allListModel = response.body();
                if (!allListModel.isError()){
                    list = response.body().getData();


                    for (int i=0; i<list.size(); i++){

                        LatLng location = new LatLng(Double.parseDouble(list.get(i).getLattitude()),
                                Double.parseDouble(list.get(i).getLongitude()));

                        Marker marker = mMap.addMarker(new MarkerOptions().position(location)
                                .title(list.get(i).getOrganizationname()));
                        MapModel info = new MapModel();

                        int height = 115;
                        int width = 70;

                        Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.marker_blood);
                        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                        BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);


                        LatLng latLng = new LatLng(Double.parseDouble(list.get(0).getLattitude()),
                                Double.parseDouble(list.get(0).getLongitude()));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng.latitude, latLng.longitude),12.0f));
                        mMap.addMarker(new MarkerOptions().position(location).icon(smallMarkerIcon));


                        if (list.size()!=0){
                            BloodHospitalInfoWindowAdapter testInfoWindowAdapter = new BloodHospitalInfoWindowAdapter(getActivity());
                            mMap.setInfoWindowAdapter(testInfoWindowAdapter);

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AllListModel> call, Throwable t) {

            }
        });


    }

    public void APICallHospital(){
        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
        Call<AllListModel> call = apiInterface.listofhospital(lat+"",lng+"");
        call.enqueue(new Callback<AllListModel>() {
            @Override
            public void onResponse(Call<AllListModel> call, Response<AllListModel> response) {
                Log.e("Selected Hospital", "onClick: "+ response.body());
                AllListModel allListModel = response.body();
                if (!allListModel.isError()){
                    list = response.body().getData();

                    RvHospitalAdapter adapter = new RvHospitalAdapter(list);
                            binding.rvMap.setAdapter(adapter);

                    for (int i=0; i<list.size(); i++){

                        LatLng location = new LatLng(Double.parseDouble(list.get(i).getLattitude()),
                                Double.parseDouble(list.get(i).getLongitude()));

                        Marker marker = mMap.addMarker(new MarkerOptions().position(location)
                                .title(list.get(i).getOrganizationname()));
                        MapModel info = new MapModel();

                        int height = 115;
                        int width = 70;

                        Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.marker_hospital);
                        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                        BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);


                        LatLng latLng = new LatLng(Double.parseDouble(list.get(0).getLattitude()),
                                Double.parseDouble(list.get(0).getLongitude()));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng.latitude, latLng.longitude),12.0f));
                        mMap.addMarker(new MarkerOptions().position(location).icon(smallMarkerIcon));


                        if (list.size()!=0){
                            HospitalInfoWindowAdapter testInfoWindowAdapter = new HospitalInfoWindowAdapter(getActivity());
                            mMap.setInfoWindowAdapter(testInfoWindowAdapter);

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AllListModel> call, Throwable t) {

            }
        });
    }

    public void APICallLab(){
        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
        Call<AllListModel> call = apiInterface.listoflab(lat+"",lng+"");
        call.enqueue(new Callback<AllListModel>() {
            @Override
            public void onResponse(Call<AllListModel> call, Response<AllListModel> response) {
                Log.e("Selected Hospital", "onClick: "+ response.body());
                AllListModel allListModel = response.body();
                if (!allListModel.isError()){
                    list = response.body().getData();

                    RvLabAdapter adapter = new RvLabAdapter(list);
                    binding.rvMap.setAdapter(adapter);

                    for (int i=0; i<list.size(); i++){

                        LatLng location = new LatLng(Double.parseDouble(list.get(i).getLattitude()),
                                Double.parseDouble(list.get(i).getLongitude()));

                        Marker marker = mMap.addMarker(new MarkerOptions().position(location)
                                .title(list.get(i).getOrganizationname()));
                        MapModel info = new MapModel();

                        int height = 115;
                        int width = 70;

                        Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.marker_labs);
                        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                        BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);


                        LatLng latLng = new LatLng(Double.parseDouble(list.get(0).getLattitude()),
                                Double.parseDouble(list.get(0).getLongitude()));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng.latitude, latLng.longitude),12.0f));
                        mMap.addMarker(new MarkerOptions().position(location).icon(smallMarkerIcon));


                        if (list.size()!=0){
                            LabInfoWindowAdapter testInfoWindowAdapter = new LabInfoWindowAdapter(getActivity());
                            mMap.setInfoWindowAdapter(testInfoWindowAdapter);

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AllListModel> call, Throwable t) {

            }
        });
    }

    public void APICallDoctor(){
        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
        Call<AllListModel> call = apiInterface.listofppd(lat+"",lng+"");
        call.enqueue(new Callback<AllListModel>() {
            @Override
            public void onResponse(Call<AllListModel> call, Response<AllListModel> response) {
                Log.e("Selected Hospital", "onClick: "+ response.body());
                AllListModel allListModel = response.body();
                if (!allListModel.isError()){
                    list = response.body().getData();

                    RvDoctorAdapter adapter = new RvDoctorAdapter(list);
                    binding.rvMap.setAdapter(adapter);

                    for (int i=0; i<list.size(); i++){

                        LatLng location = new LatLng(Double.parseDouble(list.get(i).getLattitude()),
                                Double.parseDouble(list.get(i).getLongitude()));

                        Marker marker = mMap.addMarker(new MarkerOptions().position(location)
                                .title(list.get(i).getOwnername()));
                        MapModel info = new MapModel();

                        int height = 115;
                        int width = 70;

                        Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.marker_doctor);
                        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                        BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);


                        LatLng latLng = new LatLng(Double.parseDouble(list.get(0).getLattitude()),
                                Double.parseDouble(list.get(0).getLongitude()));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng.latitude, latLng.longitude),12.0f));
                        mMap.addMarker(new MarkerOptions().position(location).icon(smallMarkerIcon));


                        if (list.size()!=0){
                            DoctorInfoWindowAdapter testInfoWindowAdapter = new DoctorInfoWindowAdapter(getActivity());
                            mMap.setInfoWindowAdapter(testInfoWindowAdapter);

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AllListModel> call, Throwable t) {

            }
        });
    }

    public void APICallBlood(){
        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
        Call<AllListModel> call = apiInterface.listofbloodbank(lat+"",lng+"");
        call.enqueue(new Callback<AllListModel>() {
            @Override
            public void onResponse(Call<AllListModel> call, Response<AllListModel> response) {
                Log.e("Selected Hospital", "onClick: "+ response.body());
                AllListModel allListModel = response.body();
                if (!allListModel.isError()){
                    list = response.body().getData();

                    RvBloodAdapter adapter = new RvBloodAdapter(list);
                    binding.rvMap.setAdapter(adapter);

                    for (int i=0; i<list.size(); i++){

                        LatLng location = new LatLng(Double.parseDouble(list.get(i).getLattitude()),
                                Double.parseDouble(list.get(i).getLongitude()));

                        Marker marker = mMap.addMarker(new MarkerOptions().position(location)
                                .title(list.get(i).getOrganizationname()));
                        MapModel info = new MapModel();

                        int height = 115;
                        int width = 70;

                        Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.marker_blood);
                        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                        BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);


                        LatLng latLng = new LatLng(Double.parseDouble(list.get(0).getLattitude()),
                                Double.parseDouble(list.get(0).getLongitude()));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng.latitude, latLng.longitude),12.0f));
                        mMap.addMarker(new MarkerOptions().position(location).icon(smallMarkerIcon));


                        if (list.size()!=0){
                            BloodHospitalInfoWindowAdapter testInfoWindowAdapter = new BloodHospitalInfoWindowAdapter(getActivity());
                            mMap.setInfoWindowAdapter(testInfoWindowAdapter);

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AllListModel> call, Throwable t) {

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

    @Override
    public void onUserClicked(int position) {


    }

}
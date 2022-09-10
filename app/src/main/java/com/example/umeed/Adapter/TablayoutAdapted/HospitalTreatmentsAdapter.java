package com.example.umeed.Adapter.TablayoutAdapted;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Models.StatusErrorModel;
import com.example.umeed.Models.TablayoutModels.HospitalTreatmentDataModel;
import com.example.umeed.R;
import com.example.umeed.databinding.RecyclerviewTreatmentsBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalTreatmentsAdapter extends RecyclerView.Adapter<HospitalTreatmentsAdapter.ViewHolder> {

    public static String Medical;
    public static String Ambulance;
    ArrayList<HospitalTreatmentDataModel> list;

    public HospitalTreatmentsAdapter(ArrayList<HospitalTreatmentDataModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewTreatmentsBinding view = RecyclerviewTreatmentsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HospitalTreatmentDataModel model = list.get(position);
        String bedname = model.getBedname();

        if (bedname.equals("")){
            holder.binding.visiblebedname.setVisibility(View.GONE);
            holder.binding.vis.setVisibility(View.GONE);
        }else {
            holder.binding.visiblebedname.setVisibility(View.VISIBLE);
            holder.binding.vis.setVisibility(View.VISIBLE);
            holder.binding.bedname.setText(model.getBedname());
        }
        holder.binding.treatments.setText(model.getTreatment());
        holder.binding.costTreatments.setText(model.getCost());
        holder.binding.descriptionTreatments.setText(model.getDescription());
        String serviceID = model.getTreatmentid();

        Intent intent = ((Activity) holder.binding.getRoot().getContext()).getIntent();
        String organizationname = intent.getStringExtra("organizationname");
        String ownername = intent.getStringExtra("ownername");
        String medicalusertype = intent.getStringExtra("medicalusertype");
        String emailid = intent.getStringExtra("emailid");
        String website = intent.getStringExtra("website");
        String mobileno = intent.getStringExtra("mobileno");
        String landlineno = intent.getStringExtra("landlineno");
        String medicalid = intent.getStringExtra("medicalid");
        String address = intent.getStringExtra("address");
        String lattitude = intent.getStringExtra("lattitude");
        String longitude = intent.getStringExtra("longitude");
        String specialization = intent.getStringExtra("specialization");
        String covidpatientaccepted = intent.getStringExtra("covidpatientaccepted");
        String ayushmancardaccepted = intent.getStringExtra("ayushmancardaccepted");
        String mediclaimaccepted = intent.getStringExtra("mediclaimaccepted");
        String emergencypatientaccepted = intent.getStringExtra("emergencypatientaccepted");
        String info = intent.getStringExtra("info");
        String type = intent.getStringExtra("type");
        String bloodbank = intent.getStringExtra("bloodbank");
        String distance = intent.getStringExtra("distance");


        SharedPreferences sharedPreferences2 = ((Activity) holder.binding.getRoot().getContext()).getSharedPreferences("credentials", Context.MODE_PRIVATE);
        if (sharedPreferences2.contains("aadharno")) {
            holder.binding.bookVis.setVisibility(View.VISIBLE);
        }else{
            holder.binding.bookVis.setVisibility(View.GONE);
        }


        holder.binding.boolTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(holder.binding.getRoot().getContext(), R.style.BookHospitalPopup);
                bottomSheetDialog.setContentView(R.layout.book_treatment_hospital);
                bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
                //                FrameLayout bottomSheet = bottomSheetDialog.findViewById(R.id.frame_layout);
                //                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
                //                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                bottomSheetDialog.setCancelable(false);

                RadioGroup RgMedical, RgAmbulance;
                RadioButton YesMedical, NoMedical, YesAmbulance, NoAmbulance;
                RgMedical = bottomSheetDialog.findViewById(R.id.RgMedical);
                RgAmbulance = bottomSheetDialog.findViewById(R.id.RgAmbulanc);
                YesMedical = bottomSheetDialog.findViewById(R.id.medicalYes);
                NoMedical = bottomSheetDialog.findViewById(R.id.medialNo);
                YesAmbulance = bottomSheetDialog.findViewById(R.id.ambulanceYes);
                NoAmbulance = bottomSheetDialog.findViewById(R.id.ambulanceNo);

                RgMedical.clearCheck();
                RgMedical.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (YesMedical.isChecked()){
                            Medical = "Yes";
                        }else{
                            Medical = "No";
                        }
                    }
                });

                RgAmbulance.clearCheck();
                RgAmbulance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (YesAmbulance.isChecked()){
                            Ambulance = "Yes";
                        }else{
                            Ambulance = "No";
                        }
                    }
                });

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

                        SharedPreferences sharedPreferences = ((Activity) holder.binding.getRoot().getContext()).getSharedPreferences("credentials", Context.MODE_PRIVATE);
                        String aadhar = sharedPreferences.getString("aadharno", "");
                        Log.e("Booking Aadhar", "onClick: "+aadhar );
                        Log.e("Booking medicalid", "onClick: "+medicalid );
                        Log.e("Booking medicalusertype", "onClick: "+medicalusertype );
                        Log.e("Booking serviceID", "onClick: "+serviceID );
                        Log.e("Booking Description", "onClick: "+bookingDescription.getText().toString() );
                        Log.e("Booking Medical", "onClick: "+Medical );
                        Log.e("Booking Ambulance", "onClick: "+Ambulance );

                        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
                        Call<StatusErrorModel> call = apiInterface.booktreatment(aadhar+"",
                        medicalid+"",medicalusertype+"",serviceID+"",
                                bookingDescription.getText().toString()+"",
                                Medical+"",Ambulance+"");

                        call.enqueue(new Callback<StatusErrorModel>() {
                            @Override
                            public void onResponse(Call<StatusErrorModel> call, Response<StatusErrorModel> response) {
                                StatusErrorModel statusErrorModel = response.body();
                                if (!statusErrorModel.isError()){
                                    bottomSheetDialog.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<StatusErrorModel> call, Throwable t) {
                                Log.e("Failed Hospital Booking", "onFailure: Booking Hospital"+t );
                            }
                        });
                    }
                });

                bottomSheetDialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerviewTreatmentsBinding binding;

        public ViewHolder(@NonNull RecyclerviewTreatmentsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

package com.example.umeed.Fragment;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Activity.BookingActivity;
import com.example.umeed.Activity.ComplaintsActivity;
import com.example.umeed.Activity.Login;
import com.example.umeed.Activity.ReportsList;
import com.example.umeed.Models.ProfileDataModel;
import com.example.umeed.Models.ProfileModel;
import com.example.umeed.R;
import com.example.umeed.databinding.FragmentAccountBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AccountFragment extends Fragment {

    FragmentAccountBinding binding;
    DownloadManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = binding.inflate(inflater, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("credentials", Context.MODE_PRIVATE);
        String aadhar = sharedPreferences.getString("aadharno","");

        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
        Call<ProfileModel> call = apiInterface.userdetails(aadhar+"");
        call.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                ProfileModel profileModel = response.body();
                if (!profileModel.isError()){
                    ProfileDataModel profileDataModel = response.body().getData();

                    String chronic = profileDataModel.getChronicdisease();
                    if(chronic.equals("Choose Chronic disease, if any"))
                        chronic="None";

                    String allergy = profileDataModel.getAllergy();
                    if(allergy.isEmpty())
                        allergy="None";

                    String ayushman = profileDataModel.getAyushmancardno();
                    if(ayushman.isEmpty())
                        ayushman="None";

                    String gender = profileDataModel.getGender();
                    if(gender.equals("Male"))
                        binding.profileImage.setImageResource(R.drawable.boy);
                    else
                        binding.profileImage.setImageResource(R.drawable.ic_girl);

                    binding.profileName.setText(profileDataModel.getFirstname() + " " + profileDataModel.getLastname());
                    binding.user.setText(profileDataModel.getFirstname() + " " + profileDataModel.getLastname());
                    binding.eMail.setText(profileDataModel.getEmailid() + "");
                    binding.age.setText(profileDataModel.getAge() + "");
                    binding.gender.setText(profileDataModel.getGender() + "");
                    binding.mobileNo.setText(profileDataModel.getMobileno() + "");
                    binding.location.setText(profileDataModel.getCity() + " , " + profileDataModel.getState());
                    binding.bGroup.setText(profileDataModel.getBloodgroup() + "");
                    binding.chronic.setText(chronic + "");
                    binding.allergy.setText(allergy + "");
                    binding.ayushmanNo.setText(ayushman + "");
                    binding.firstemergency.setText(profileDataModel.getEmergencycontact1());
                    binding.secondemergency.setText(profileDataModel.getEmergencycontact2());
                }
            }
            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
            }
        });

        binding.bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                manager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
//                Uri uri = Uri.parse("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
//                DownloadManager.Request request = new DownloadManager.Request(uri);
//                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
//                long reference = manager.enqueue(request);

                startActivity(new Intent(getActivity(), BookingActivity.class));
            }
        });

        binding.Reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ReportsList.class));
            }
        });

        binding.complaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ComplaintsActivity.class));
            }
        });

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getActivity().getSharedPreferences("credentials",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(getActivity(), Login.class));
                getActivity().finish();
            }
        });
        return binding.getRoot();
    }
}
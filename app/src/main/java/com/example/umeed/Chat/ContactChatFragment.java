package com.example.umeed.Chat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Adapter.DoctorListAdapter;
import com.example.umeed.Adapter.HospitalListAdapter;
import com.example.umeed.Models.AllListDataModel;
import com.example.umeed.Models.AllListModel;
import com.example.umeed.R;
import com.example.umeed.databinding.FragmentContactChatBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactChatFragment extends Fragment {

    FragmentContactChatBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = binding.inflate(inflater, container, false);

        ArrayList<AllListDataModel> list = new ArrayList<>();

//        HospitalListAdapter adapter = new HospitalListAdapter(list);
//        binding.rvContactList.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.rvContactList.setLayoutManager(linearLayoutManager);

        rvDoctor();

        return binding.getRoot();

    }

    private void rvDoctor() {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("credentials", Context.MODE_PRIVATE);
        String aadhar = sharedPreferences.getString("aadharno", "");

        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
        Call<ViewChatContactModel> call = apiInterface.viewchatcontacts(aadhar+"");
        call.enqueue(new Callback<ViewChatContactModel>() {
            @Override
            public void onResponse(Call<ViewChatContactModel> call, Response<ViewChatContactModel> response) {

                Log.e("Chat List Data", "onResponse: " + response.body().getData());
                ViewChatContactModel model = response.body();
                if (!model.isError()) {
                    if (model.getData() != null) {
                        ViewChatContactModel list = response.body();
                        Log.e("Doctor List Data", "onResponse: " + list);
                        ChatContactAdapter adapter = new ChatContactAdapter(list.getData());
                        binding.rvContactList.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ViewChatContactModel> call, Throwable t) {
                Log.e("Doctor List Data", "Error: " + t);
            }
        });

    }
}
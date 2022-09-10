package com.example.umeed.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umeed.Activity.BloodInfo;
import com.example.umeed.Activity.BookingInfoActivity;
import com.example.umeed.Models.AllListDataModel;
import com.example.umeed.Models.BookingDataModel;
import com.example.umeed.databinding.RecyclerviewBookingBinding;

import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder>{

    ArrayList<BookingDataModel> list;

    public BookingAdapter(ArrayList<BookingDataModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewBookingBinding view = RecyclerviewBookingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookingDataModel model = list.get(position);
        holder.binding.orgName.setText(model.getOrganizationname());
        holder.binding.TreatmentName.setText(model.getTreatmentname());
        holder.binding.BookingID.setText(model.getBookingid());
        holder.binding.Status.setText(model.getStatus());
        holder.binding.Type.setText(model.getMedicalusertype());

        String bookingid = model.getBookingid();
        Log.e("Type", "onBindViewHolder: "+ model.getMedicalusertype());

        String Organizationname = model.getOrganizationname();
        Log.e("Organizationname11", "onBindViewHolder: "+Organizationname );
        holder.binding.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.binding.getRoot().getContext(), BookingInfoActivity.class);
                intent.putExtra("organizationname", Organizationname);
                intent.putExtra("bookingid", bookingid);
                holder.binding.getRoot().getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewBookingBinding binding;

        public ViewHolder(@NonNull RecyclerviewBookingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

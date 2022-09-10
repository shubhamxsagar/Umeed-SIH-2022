package com.example.umeed.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umeed.Activity.BookingInfoActivity;
import com.example.umeed.Models.ComplaintsDataModel;
import com.example.umeed.databinding.RecyclerviewBookingBinding;
import com.example.umeed.databinding.RecyclerviewComplaintsBinding;

import java.util.ArrayList;

public class ComplaintsAdapter extends RecyclerView.Adapter<ComplaintsAdapter.ViewHolder>{

    ArrayList<ComplaintsDataModel> list;

    public ComplaintsAdapter(ArrayList<ComplaintsDataModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewComplaintsBinding view = RecyclerviewComplaintsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ComplaintsDataModel model = list.get(position);
        holder.binding.complaintid.setText(model.getComplainid());
        holder.binding.Type.setText(model.getType());
        holder.binding.BookingID.setText(model.getBookingid());
        holder.binding.Status.setText(model.getStatus());
        holder.binding.Description.setText(model.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewComplaintsBinding binding;

        public ViewHolder(@NonNull RecyclerviewComplaintsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

package com.example.umeed.Adapter.TablayoutAdapted;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umeed.Models.TablayoutModels.HospitalAvailabilityDataModel;
import com.example.umeed.databinding.RecyclerviewAvailabilityBinding;

import java.util.ArrayList;

public class HospitalAvailabilityAdapter extends RecyclerView.Adapter<HospitalAvailabilityAdapter.ViewHolder>{

    ArrayList<HospitalAvailabilityDataModel> list;

    public HospitalAvailabilityAdapter(ArrayList<HospitalAvailabilityDataModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewAvailabilityBinding view = RecyclerviewAvailabilityBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HospitalAvailabilityDataModel model = list.get(position);
        holder.binding.totalnumberavailable.setText(model.getTotalnumberavailable());
        holder.binding.bedhold.setText(model.getBedonhold());
        holder.binding.availablebeds1.setText(model.getCurrentavailablity());
        holder.binding.availablebeds2.setText(model.getCurrentavailablity());
        holder.binding.type.setText(model.getType());
        holder.binding.oxygenprovided.setText(model.getOxygenprovided());
        holder.binding.facility.setText(model.getFacility());
        holder.binding.cost.setText(model.getCost());
        holder.binding.bedname.setText(model.getBedname());
        holder.binding.costdifferencefromgovernment.setText(model.getCostdifferencefromgovernment());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerviewAvailabilityBinding binding;

        public ViewHolder(@NonNull RecyclerviewAvailabilityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
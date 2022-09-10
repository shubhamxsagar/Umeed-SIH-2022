package com.example.umeed.Adapter.TablayoutAdapted;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umeed.Models.TablayoutModels.BloodAvailabilityDataModel;
import com.example.umeed.Models.TablayoutModels.HospitalAvailabilityDataModel;
import com.example.umeed.databinding.RecyclerviewAvailabilityBinding;
import com.example.umeed.databinding.RecyclerviewBloodAvailabilityBinding;

import java.util.ArrayList;

public class BloodAvailabilityAdapter extends RecyclerView.Adapter<BloodAvailabilityAdapter.ViewHolder>{

    ArrayList<BloodAvailabilityDataModel> list;

    public BloodAvailabilityAdapter(ArrayList<BloodAvailabilityDataModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewBloodAvailabilityBinding view = RecyclerviewBloodAvailabilityBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BloodAvailabilityDataModel model = list.get(position);
        holder.binding.BloodType.setText(model.getBloodgroup());

        String BP = model.getBloodgroup().toString();

        if (BP.equals("B")){
            holder.binding.BloodType.setText("B+");
        }
        if (model.getBloodgroup().toString().equals("A")){
            holder.binding.BloodType.setText("A+");
        }
        if (model.getBloodgroup().toString().equals("O")){
            holder.binding.BloodType.setText("O+");
        }
        if (model.getBloodgroup().toString().equals("AB")){
            holder.binding.BloodType.setText("AB+");
        }
        holder.binding.BloodUnit.setText(model.getAvailable());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerviewBloodAvailabilityBinding binding;

        public ViewHolder(@NonNull RecyclerviewBloodAvailabilityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
package com.example.umeed.Map;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umeed.Models.AllListDataModel;
import com.example.umeed.databinding.RecyclerviewBloodMapListBinding;
import com.example.umeed.databinding.RecyclerviewDoctorListBinding;

import java.util.ArrayList;

public class RvDoctorAdapter  extends RecyclerView.Adapter<RvDoctorAdapter.ViewHolder> {

    ArrayList<AllListDataModel> list;

    public RvDoctorAdapter(ArrayList<AllListDataModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewDoctorListBinding view = RecyclerviewDoctorListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AllListDataModel model = list.get(position);
        holder.binding.Doctor.setText(model.getOwnername());
        holder.binding.Phone.setText(model.getMobileno());
        holder.binding.Email.setText(model.getEmailid());
        holder.binding.Website.setText(model.getWebsite());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewDoctorListBinding binding;

        public ViewHolder(@NonNull RecyclerviewDoctorListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}

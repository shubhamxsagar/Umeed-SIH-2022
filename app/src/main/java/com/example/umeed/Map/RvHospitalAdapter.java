package com.example.umeed.Map;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umeed.Adapter.HospitalListAdapter;
import com.example.umeed.Adapter.MapListAdapter;
import com.example.umeed.Models.AllListDataModel;
import com.example.umeed.R;
import com.example.umeed.databinding.LayoutHospitalListBinding;
import com.example.umeed.databinding.RecyclerviewLabMapListBinding;
import com.example.umeed.databinding.RecyclerviewMapListBinding;

import java.util.ArrayList;

public class RvHospitalAdapter  extends RecyclerView.Adapter<RvHospitalAdapter.ViewHolder> {

    ArrayList<AllListDataModel> list;

    public RvHospitalAdapter(ArrayList<AllListDataModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewMapListBinding view = RecyclerviewMapListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AllListDataModel model = list.get(position);
        holder.binding.hospital.setText(model.getOrganizationname());
        holder.binding.Phone.setText(model.getMobileno());
        holder.binding.Email.setText(model.getEmailid());
        holder.binding.Website.setText(model.getWebsite());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewMapListBinding binding;

        public ViewHolder(@NonNull RecyclerviewMapListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}

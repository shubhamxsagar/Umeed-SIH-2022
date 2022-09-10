package com.example.umeed.Map;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umeed.Models.AllListDataModel;
import com.example.umeed.databinding.RecyclerviewLabMapListBinding;
import com.example.umeed.databinding.RecyclerviewMapListBinding;

import java.util.ArrayList;

public class RvLabAdapter  extends RecyclerView.Adapter<RvLabAdapter.ViewHolder> {

    ArrayList<AllListDataModel> list;

    public RvLabAdapter(ArrayList<AllListDataModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewLabMapListBinding view = RecyclerviewLabMapListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AllListDataModel model = list.get(position);
        holder.binding.Lab.setText(model.getOrganizationname());
        holder.binding.Phone.setText(model.getMobileno());
        holder.binding.Email.setText(model.getEmailid());
        holder.binding.Website.setText(model.getWebsite());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewLabMapListBinding binding;

        public ViewHolder(@NonNull RecyclerviewLabMapListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}

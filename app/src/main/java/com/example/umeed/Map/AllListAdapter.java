package com.example.umeed.Map;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umeed.Models.AllListDataModel;
import com.example.umeed.databinding.RecyclerviewBloodMapListBinding;

import java.util.ArrayList;

public class AllListAdapter extends RecyclerView.Adapter<AllListAdapter.ViewHolder> {

        ArrayList<AllListDataModel> list;

public AllListAdapter(ArrayList<AllListDataModel> list) {
        this.list = list;
        }

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewBloodMapListBinding view = RecyclerviewBloodMapListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AllListDataModel model = list.get(position);
        holder.binding.Blood.setText(model.getOrganizationname());
        holder.binding.Phone.setText(model.getMobileno());
        holder.binding.Email.setText(model.getEmailid());
        holder.binding.Website.setText(model.getWebsite());
        }

@Override
public int getItemCount() {
        return list.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder {
    RecyclerviewBloodMapListBinding binding;

    public ViewHolder(@NonNull RecyclerviewBloodMapListBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

    }
}
}

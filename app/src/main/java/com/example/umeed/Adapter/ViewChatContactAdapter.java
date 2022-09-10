package com.example.umeed.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umeed.Chat.ViewChatContactDataModel;
import com.example.umeed.databinding.LayoutLabListBinding;

import java.util.ArrayList;

public class ViewChatContactAdapter  extends RecyclerView.Adapter<ViewChatContactAdapter.ViewHolder>{

    ArrayList<ViewChatContactDataModel> list;

    public ViewChatContactAdapter(ArrayList<ViewChatContactDataModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewChatContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutLabListBinding view = LayoutLabListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewChatContactAdapter.ViewHolder holder, int position) {
        ViewChatContactDataModel model = list.get(position);
        String x = model.getContact().toString();
        holder.binding.nameHospital.setText(model.getContact());
        Log.e("Contact", "onBindViewHolder: Contact"+x );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LayoutLabListBinding binding;
        public ViewHolder(@NonNull LayoutLabListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

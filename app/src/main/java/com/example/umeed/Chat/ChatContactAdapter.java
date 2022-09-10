package com.example.umeed.Chat;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umeed.Activity.DoctorInfo;
import com.example.umeed.Adapter.BloodListAdapter;
import com.example.umeed.Models.AllListDataModel;
import com.example.umeed.databinding.LayoutBloodListBinding;
import com.example.umeed.databinding.RvContactChatLayoutBinding;

import java.util.ArrayList;

public class ChatContactAdapter extends RecyclerView.Adapter<ChatContactAdapter.ViewHolder>{

    ArrayList<ViewChatContactDataModel> list;

    public ChatContactAdapter(ArrayList<ViewChatContactDataModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvContactChatLayoutBinding view = RvContactChatLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewChatContactDataModel model = list.get(position);
        holder.binding.Contact.setText(model.getContact());
        holder.binding.ContactsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.binding.getRoot().getContext(), ChatInfo.class);
                intent.putExtra("receiver", model.getContact());
                holder.binding.getRoot().getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        RvContactChatLayoutBinding binding;

        public ViewHolder(@NonNull RvContactChatLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

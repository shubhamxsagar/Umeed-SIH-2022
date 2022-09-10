package com.example.umeed.Chat;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umeed.Adapter.HospitalListAdapter;
import com.example.umeed.R;
import com.example.umeed.databinding.LayoutHospitalListBinding;
import com.example.umeed.databinding.RvChatLayoutBinding;
import com.example.umeed.databinding.SenderchatlayoutBinding;

import java.util.ArrayList;

public class ChatingMessageAdapter extends RecyclerView.Adapter<ChatingMessageAdapter.ViewHolder>{

    ArrayList<MessageDataModel> messagesArrayList;

    public ChatingMessageAdapter(ArrayList<MessageDataModel> messagesArrayList) {
        this.messagesArrayList = messagesArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvChatLayoutBinding view = RvChatLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatingMessageAdapter.ViewHolder holder, int position) {
        MessageDataModel model = messagesArrayList.get(position);
        SharedPreferences sharedPreferences = holder.binding.getRoot().getContext().getSharedPreferences("credentials", Context.MODE_PRIVATE);
        String aadhar = sharedPreferences.getString("aadharno", "");

//        ConstraintLayout constraintLayout = holder.binding.ccLayout;
        holder.binding.message.setText(model.getMessage());

        Log.e("Sender", "onBindViewHolder: "+model.getSender());
        Log.e("Receiver", "onBindViewHolder: "+model.getReceiver());

        if(aadhar.equals(model.getSender()))
        {
            holder.binding.message.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
//            ConstraintSet constraintSet = new ConstraintSet();
//            constraintSet.clone(constraintLayout);
//            constraintSet.clear(R.id.message, ConstraintSet.LEFT);
////            constraintSet.clear(R.id.message, ConstraintSet.RIGHT);
//            constraintSet.applyTo(constraintLayout);
        }
        else
        {
            holder.binding.message.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
//            ConstraintSet constraintSet = new ConstraintSet();
//            constraintSet.clone(constraintLayout);
//            constraintSet.clear(R.id.message, ConstraintSet.RIGHT);
////            constraintSet.clear(R.id.message, ConstraintSet.LEFT);
//            constraintSet.applyTo(constraintLayout);
        }
    }

    @Override
    public int getItemCount() {
        return messagesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RvChatLayoutBinding binding;

        public ViewHolder(@NonNull RvChatLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}

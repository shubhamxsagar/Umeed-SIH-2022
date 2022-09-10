package com.example.umeed.Chat;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umeed.R;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<MessageDataModel> messagesArrayList;

    int ITEM_SEND=1;
    int ITEM_RECIEVE=2;

    public MessagesAdapter(Context context, ArrayList<MessageDataModel> messagesArrayList) {
        this.context = context;
        this.messagesArrayList = messagesArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==ITEM_SEND)
        {
            View view= LayoutInflater.from(context).inflate(R.layout.senderchatlayout,parent,false);
            return new SenderViewHolder(view);
        }
        else
        {
            View view= LayoutInflater.from(context).inflate(R.layout.receiverchatlayout,parent,false);
            return new RecieverViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MessageDataModel messages=messagesArrayList.get(position);
        SharedPreferences sharedPreferences = context.getSharedPreferences("credentials", Context.MODE_PRIVATE);
        String aadhar = sharedPreferences.getString("aadharno", "");

        if(holder.getClass()==SenderViewHolder.class)
        {

            SenderViewHolder viewHolder=(SenderViewHolder)holder;
            viewHolder.textViewmessaage.setText(messages.getMessage());
            viewHolder.textViewmessaage.setTextIsSelectable(true);
            viewHolder.timeofmessage.setText(messages.getTimestamp());
        }
        else
        {
            Log.e("Showing message", "onBindViewHolder: "+messages.getMessage() );
//            Log.e("Showing message", "onBindViewHolder: "+messages.getSender() );
//            Log.e("Showing message", "onBindViewHolder: "+messages.getTimestamp() );
            RecieverViewHolder viewHolder=(RecieverViewHolder)holder;
            viewHolder.RtextViewmessaage.setText(messages.getMessage());
            viewHolder.RtextViewmessaage.setTextIsSelectable(true);
            viewHolder.Rtimeofmessage.setText(messages.getTimestamp());
        }
    }

    String userid="sender";


    @Override
    public int getItemViewType(int position) {
        MessageDataModel messages=messagesArrayList.get(position);
        SharedPreferences sharedPreferences = context.getSharedPreferences("credentials", Context.MODE_PRIVATE);
        String aadhar = sharedPreferences.getString("aadharno", "");
        if(aadhar.equals(messages.getSender()))

        {
            return  ITEM_SEND;
        }
        else
        {
            return ITEM_RECIEVE;
        }
    }

    @Override
    public int getItemCount() {
        return messagesArrayList.size();
    }

    class SenderViewHolder extends RecyclerView.ViewHolder
    {

        TextView textViewmessaage;
        TextView timeofmessage;


        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewmessaage=itemView.findViewById(R.id.sendermessage);
            timeofmessage=itemView.findViewById(R.id.timeofmessage);
        }
    }

    class RecieverViewHolder extends RecyclerView.ViewHolder
    {

        TextView RtextViewmessaage;
        TextView Rtimeofmessage;


        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);
            RtextViewmessaage=itemView.findViewById(R.id.Rsendermessage);
            Rtimeofmessage=itemView.findViewById(R.id.Rtimeofmessage);
        }
    }

}
package com.example.umeed.Chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Adapter.DoctorListAdapter;
import com.example.umeed.Models.AllListModel;
import com.example.umeed.Models.StatusErrorModel;
import com.example.umeed.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatInfo extends AppCompatActivity {

    TextView Nameofspecificdoctor;
    EditText mgetmessage;
    ImageButton msendmessagebutton;
    CardView msendmessagecardview;
    androidx.appcompat.widget.Toolbar mtoolbarofspecificchat;
    TextView mnameofspecificuser;
    private String enteredmessage;
    Intent intent;
    String mrecieveruid,msenderuid;
    String senderroom,recieverroom;
    ImageButton mbackbuttonofspecificchat;
    RecyclerView mmessagerecyclerview;
    String currenttime;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    MessagesAdapter messagesAdapter;
    ArrayList<MessageDataModel> messagesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_info);


        mgetmessage=findViewById(R.id.getmessage);
        msendmessagecardview=findViewById(R.id.carviewofsendmessage);
        msendmessagebutton=findViewById(R.id.imageviewsendmessage);
        mtoolbarofspecificchat=findViewById(R.id.toolbarofspecificchat);
        mnameofspecificuser=findViewById(R.id.Nameofspecificdoctor);
        mbackbuttonofspecificchat=findViewById(R.id.backbuttonofspecificchat);

        messagesArrayList=new ArrayList<>();
        mmessagerecyclerview=findViewById(R.id.recyclerviewofspecific);



        Intent intent2 = getIntent();
        String organizationname = intent2.getStringExtra("organizationname");
        String ownername = intent2.getStringExtra("ownername");

        Nameofspecificdoctor = findViewById(R.id.Nameofspecificdoctor);
        if (Nameofspecificdoctor == null){
            Nameofspecificdoctor.setText(organizationname);
        }else{
            Nameofspecificdoctor.setText(ownername);
        }

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        mmessagerecyclerview.setLayoutManager(linearLayoutManager);
        messagesAdapter=new MessagesAdapter(ChatInfo.this,messagesArrayList);
        mmessagerecyclerview.setAdapter(messagesAdapter);

        intent=getIntent();

        calendar=Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("hh:mm a");

        msenderuid="000000000004";
        String sample="hello";
        String sample1="hello";
//        mrecieveruid=getIntent().getStringExtra("receiveruid");

//        senderroom=msenderuid+mrecieveruid;
//        recieverroom=mrecieveruid+msenderuid;

        messagesAdapter=new MessagesAdapter(ChatInfo.this,messagesArrayList);
        mbackbuttonofspecificchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Date date=new Date();
//        currenttime=simpleDateFormat.format(calendar.getTime());
//        MessageDataModel messages=new MessageDataModel(""+sample,""+sample1,"",""+currenttime);
//        messagesArrayList.add(messages);

        rvMessageCall();

        SharedPreferences sharedPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        String aadhar = sharedPreferences.getString("aadharno", "");

        Intent intent = getIntent();
        String receiver = intent.getStringExtra("receiver");
        Log.e("Receiver Chat", "onCreate: "+receiver );

        Intent intent3 = getIntent();
        String firstname = intent3.getStringExtra("firstname");
        String lastname = intent3.getStringExtra("lastname");
        String mobile = intent3.getStringExtra("mobile");
        String city = intent3.getStringExtra("city");
        String state = intent3.getStringExtra("state");
        String lat = intent3.getStringExtra("lat");
        String lng = intent3.getStringExtra("lng");
        String emergency1 = intent3.getStringExtra("emergency1");
        String emergency2 = intent3.getStringExtra("emergency2");

        if (receiver.equals("emergencydepartment")){
            enteredmessage = "Name: "+firstname +" "+ lastname+ " Mobile: " + mobile;

            ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
            Call<StatusErrorModel> call = apiInterface.sendmessages(aadhar+"",receiver+"",enteredmessage+"");
            call.enqueue(new Callback<StatusErrorModel>() {
                @Override
                public void onResponse(Call<StatusErrorModel> call, Response<StatusErrorModel> response) {
                    StatusErrorModel model = response.body();
                    if (!model.isError()){
                        rvMessageCall();
                    }
                }

                @Override
                public void onFailure(Call<StatusErrorModel> call, Throwable t) {
                    Log.e("Doctor List Data", "Error: "+ t);
                }
            });

            rvMessageCall();
        }

        if (receiver.equals("emergencydepartment")){
            enteredmessage = "https://www.google.com/maps?q=" + lat +","+lng+"&z=17&hl=en";

            ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
            Call<StatusErrorModel> call = apiInterface.sendmessages(aadhar+"",receiver+"",enteredmessage+"");
            call.enqueue(new Callback<StatusErrorModel>() {
                @Override
                public void onResponse(Call<StatusErrorModel> call, Response<StatusErrorModel> response) {
                    StatusErrorModel model = response.body();
                    if (!model.isError()){
                        rvMessageCall();
                    }
                }

                @Override
                public void onFailure(Call<StatusErrorModel> call, Throwable t) {
                    Log.e("Doctor List Data", "Error: "+ t);
                }
            });

            rvMessageCall();
        }


        msendmessagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                enteredmessage=mgetmessage.getText().toString();

                if(enteredmessage.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter message first",Toast.LENGTH_SHORT).show();
                }

                else

                {

                    ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
                    Call<StatusErrorModel> call = apiInterface.sendmessages(aadhar+"",receiver+"",enteredmessage+"");
                    call.enqueue(new Callback<StatusErrorModel>() {
                        @Override
                        public void onResponse(Call<StatusErrorModel> call, Response<StatusErrorModel> response) {
                            StatusErrorModel model = response.body();
                            if (!model.isError()){
                                rvMessageCall();
                            }
                        }

                        @Override
                        public void onFailure(Call<StatusErrorModel> call, Throwable t) {
                            Log.e("Doctor List Data", "Error: "+ t);
                        }
                    });

                    rvMessageCall();


                }

            }
        });
    }

    public void rvMessageCall() {

        SharedPreferences sharedPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        String aadhar = sharedPreferences.getString("aadharno", "");

        Intent intent = getIntent();
        String receiver = intent.getStringExtra("receiver");

        ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
        Call<MessageModel> call = apiInterface.messages(aadhar+"",receiver+"");
        call.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                MessageModel model = response.body();
                MessageModel model1 = response.body();
                if (!model.isError()){
                    if (model.getData()!= null){
                        MessageModel list = response.body();

                        MessageDataModel messageDataModel;
//                        Log.e("Message Chat Data", "onResponse: Messages"+ list.getData());
//                        MessagesAdapter adapter = new MessagesAdapter(ChatInfo.this,list.getData());
//                        MessageDataModel messages=new MessageDataModel(""+enteredmessage,""+msenderuid,"jhjhjhjhj",""+currenttime);
//                        MessageDataModel messages1=new MessageDataModel(""+enteredmessage,""+sample1,"",""+currenttime);
//                        messagesArrayList.add(messages);
//                        messagesArrayList.add(messages1);

                        MessagesAdapter adapter = new MessagesAdapter(ChatInfo.this,list.getData());
                        mmessagerecyclerview.setAdapter(adapter);

//                        calendar=Calendar.getInstance();
//                        Date date=new Date();
//                        currenttime=simpleDateFormat.format(calendar.getTime());
//
//                        mmessagerecyclerview.setAdapter(adapter);
//                        MessageDataModel messages=new MessageDataModel("000000000004","000000000004",enteredmessage+"",""+currenttime);
//                        MessageDataModel messages1=new MessageDataModel("000000000003","000000000003",model.getData()+"",""+currenttime);
//                        messagesArrayList.add(messages);
//                        messagesArrayList.add(messages1);
                        mgetmessage.setText(null);
                        mmessagerecyclerview.scrollToPosition(messagesArrayList.size()-1);
                        messagesAdapter.notifyDataSetChanged();


//                        mmessagerecyclerview.scrollToPosition(messagesArrayList.size()-1);
                    }
                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                Log.e("Doctor List Data", "Error: "+ t);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        messagesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(messagesAdapter!=null)
        {
            messagesAdapter.notifyDataSetChanged();
        }
    }



}
package com.example.umeed.APIs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {

//    private static final String url = "http://192.168.159.7/ummed/api/";
    private static final String url = "http://192.168.204.111/ummed/api/";
//    private static final String url = "http://10.10.38.133/u/mmed/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}

package com.example.umeed.APIs;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoogleController {

    private static final String url = "http://mocki.io/";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}

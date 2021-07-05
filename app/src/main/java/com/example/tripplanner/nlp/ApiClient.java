package com.example.tripplanner.nlp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit retrofit;

    public static NlpApis getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.nyckel.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit.create(NlpApis.class);
    }

}

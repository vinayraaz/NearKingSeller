package com.nearkingseller.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vinay on 12/8/2017.
 */

public class ApiClient {
    public static final String BASE_URL = "http://54.169.90.233:9000/";
   // public static final String BASE_URL = "http://52.77.247.163:9000/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

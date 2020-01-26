package com.example.film.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieConfigRetrofit {
    private Retrofit retrofit;
    private static String BASE_URL = "https://api.themoviedb.org/";

    public MovieApi getDataMovie (){

        if(retrofit == null) {
             retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(MovieApi.class);
    }

}

package com.example.film.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {
    private Retrofit retrofit;
    private static String BASE_URL_MOVIE = "https://api.themoviedb.org/3/";

    public MovieApi getDataMovie (){

        if(retrofit == null) {
             retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL_MOVIE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(MovieApi.class);
    }

}

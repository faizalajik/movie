package com.example.film.service;

import com.example.film.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("3/discover/movie?api_key=7fd843b47a88b5d34635d7a0dd54e431")
    Call<MovieResponse> getMovie(@Query("language")String language);


}

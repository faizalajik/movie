package com.example.film.service;

import com.example.film.model.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TvShowApi {
    @GET ("3/discover/tv?api_key=7fd843b47a88b5d34635d7a0dd54e431")
    Call<TvShowResponse> getTvShow(@Query("language")String language);
}

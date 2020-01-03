package com.example.film.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.film.model.Movie;
import com.example.film.model.MovieResponse;
import com.example.film.service.MovieConfigRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Movie>> listMovie = new MutableLiveData<>();

    public void setMovie(String language){

         MovieConfigRetrofit configRetrofit = new MovieConfigRetrofit();
         configRetrofit.getDataMovie().getMovie("en-US").enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
               MovieResponse movieResponse = response.body();
               if (response.body().getResults()!= null) {
                   ArrayList<Movie> movies = movieResponse.getResults();
                   listMovie.postValue(movies);
               }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
            Log.d("MovieViewModel","onFailure"+t.getMessage());
            }
        });
   }
//
    public MutableLiveData<ArrayList<Movie>> getDataMovie(){
        return listMovie;
    }

}

package com.example.film.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.film.model.Movie;
import com.example.film.model.MovieResponse;
import com.example.film.model.TvShow;
import com.example.film.model.TvShowResponse;
import com.example.film.service.MovieConfigRetrofit;
import com.example.film.service.TvShowConfigRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Movie>> listMovie = new MutableLiveData<>();
    private MutableLiveData<ArrayList<TvShow>> listTvShow = new MutableLiveData<>();

    public void setMovie(String language) {

        MovieConfigRetrofit configRetrofit = new MovieConfigRetrofit();
        configRetrofit.getDataMovie().getMovie(language).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                if (response.body().getResults() != null) {
                    ArrayList<Movie> movies = movieResponse.getResults();
                    listMovie.postValue(movies);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("HomeViewModel", "onFailure" + t.getMessage());
            }
        });
    }

    public void setTvShow(String language) {

        TvShowConfigRetrofit configRetrofit = new TvShowConfigRetrofit();
        configRetrofit.getDataTvShow().getTvShow(language).enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                TvShowResponse tvShowResponse = response.body();
                if (response.body().getResults() != null) {
                    ArrayList<TvShow> tvShows = tvShowResponse.getResults();
                    listTvShow.postValue(tvShows);
                }
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                Log.d("HomeViewModel", "onFailure" + t.getMessage());
            }
        });
    }

    public MutableLiveData<ArrayList<Movie>> getDataMovie() {
        return listMovie;
    }

    public MutableLiveData<ArrayList<TvShow>> getDataTvShow() {
        return listTvShow;
    }

}

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

public class SearchMovieViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Movie>> listsearchMovie = new MutableLiveData<>();
    public void setSearchMovie (String language, String query){
        MovieConfigRetrofit configRetrofit = new MovieConfigRetrofit();
        configRetrofit.getDataMovie().getSearchMovie(language,query).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                if(response.body().getResults() != null){
                    ArrayList<Movie> searchmovies = movieResponse.getResults();
                    listsearchMovie.postValue(searchmovies);
                }
            }
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("Error Search",t.getMessage());
            }
        });
    }
    public MutableLiveData<ArrayList<Movie>> getSearchMovie(){
        return listsearchMovie;
    }
}

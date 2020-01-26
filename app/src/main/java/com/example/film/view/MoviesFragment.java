package com.example.film.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.film.R;
import com.example.film.adapter.MovieAdapter;
import com.example.film.model.Movie;
import com.example.film.viewmodel.HomeViewModel;

import java.util.ArrayList;

public class MoviesFragment extends Fragment {

    private RecyclerView rv;
    private String language;
    private HomeViewModel homeViewModel;
    private ProgressBar pbMovie;
    private  MovieAdapter movieAdapter;
    public MoviesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieAdapter = new MovieAdapter();
        rv = view.findViewById(R.id.rv_movie);
        pbMovie = view.findViewById(R.id.pbMovie);
        rv.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);

        language = getResources().getString(R.string.code_language);
        homeViewModel = new ViewModelProvider(
                this, new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);
        homeViewModel.setMovie(language);

        homeViewModel.getDataMovie().observe(this, new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                if(movies != null) {
                    rv.setAdapter(new MovieAdapter(getActivity(),movies));
                    showProgressBar(false);
                }else {
                    showProgressBar(true);
                }
            }
        });
    }

    private void showProgressBar (Boolean state){
        if(state){
            pbMovie.setVisibility(View.VISIBLE);
        }else {
            pbMovie.setVisibility(View.GONE);
        }
    }
}

package com.example.film;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.film.Model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment {

    private RecyclerView rv;
    private List<Movie> movieList;
    private String[] title, description, movieYear, movieRuntime, movieGenre;
    private TypedArray img;
    private List<Movie> dataMovie;
    private Movie movie;

    public MoviesFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movies, container, false);

        rv = v.findViewById(R.id.rv_movie);
        rv.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        initData();
        addData();
        rv.setAdapter(new MovieAdapter(this.getActivity(), dataMovie));

        return v;
    }

    public void initData() {
        title = getResources().getStringArray(R.array.title);
        description = getResources().getStringArray(R.array.description);
        movieYear = getResources().getStringArray(R.array.year);
        movieGenre = getResources().getStringArray(R.array.genre);
        movieRuntime = getResources().getStringArray(R.array.runtime);
        img = getResources().obtainTypedArray(R.array.image);
    }

    public void addData() {
        dataMovie = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            movie = new Movie();
            movie.settitle(title[i]);
            movie.setDetail(description[i]);
            movie.setGenre(movieGenre[i]);
            movie.setYear(movieYear[i]);
            movie.setRuntime(movieRuntime[i]);
            movie.setImg(img.getResourceId(i, -1));
            dataMovie.add(movie);
        }
    }
}

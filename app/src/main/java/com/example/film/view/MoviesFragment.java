package com.example.film.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.film.R;
import com.example.film.adapter.MovieAdapter;
import com.example.film.database.MovieDatabase;
import com.example.film.database.MovieDb;
import com.example.film.model.Movie;
import com.example.film.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment {

    private RecyclerView rv;
    private String language, state;
    private HomeViewModel homeViewModel;
    private ProgressBar pbMovie;
    private MovieAdapter movieAdapter;
    private MovieDatabase db;
    private SwipeRefreshLayout swipe;
    private ArrayList<Movie> movies;

    public MoviesFragment() {

    }

    public MoviesFragment(String state) {
        this.state = state;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = view.findViewById(R.id.rv_movie);
        pbMovie = view.findViewById(R.id.pbMovie);
        swipe = view.findViewById(R.id.swipe_movie);
        movieAdapter = new MovieAdapter();
        movies = new ArrayList<>();


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        language = getResources().getString(R.string.code_language);
        if (savedInstanceState != null && state == null) {
            state = savedInstanceState.getString("state");
            addItem();
        }

        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                addItem();
                if (state.equals("fav") && movies.isEmpty()) {
                    swipe.setRefreshing(false);
                    Toast.makeText(getContext(), "Favorite Empty", Toast.LENGTH_SHORT).show();
                } else {
                    swipe.setRefreshing(false);
                }
            }
        });
        showProgressBar(true);
        addItem();
        movieAdapter = new MovieAdapter(getContext(),movies);
        rv.setAdapter(movieAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        addItem();
    }

    private void addItem() {
        if (state.equals("home")) {
            homeViewModel = new ViewModelProvider(
                    this, new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);
            homeViewModel.setMovie(language);

            homeViewModel.getDataMovie().observe(this, new Observer<ArrayList<Movie>>() {
                @Override
                public void onChanged(ArrayList<Movie> movie) {
                    if (movie != null) {
                        rv.setAdapter(new MovieAdapter(getActivity(), movie));
                        showProgressBar(false);
                    } else {
                        showProgressBar(true);
                    }
                }
            });
           movieAdapter.notifyDataSetChanged();
        } else {
            db = Room.databaseBuilder(getContext(), MovieDatabase.class, "favorite-database")
                    .allowMainThreadQueries().build();
            List<MovieDb> movieDbs = db.movieDao().getMovie("movie");
            movies.clear();
            movieAdapter.notifyDataSetChanged();
            System.out.println(movies);
            if (movieDbs != null) {
                for (int i = 0; i < movieDbs.size(); i++) {
                    Movie movie = new Movie();
                    movie.setId(movieDbs.get(i).getId());
                    movie.setTitle(movieDbs.get(i).getTitle());
                    movie.setOverview(movieDbs.get(i).getOverview());
                    movie.setPosterPath(movieDbs.get(i).getPosterPath());
                    movie.setVoteAverage(movieDbs.get(i).getAverage());
                    movie.setReleaseDate(movieDbs.get(i).getYear());
                    movies.add(movie);
                }

//                movieAdapter = new MovieAdapter(getContext(),movies);
//                rv.setAdapter(movieAdapter);
                showProgressBar(false);
            } else {
                showProgressBar(true);
            }

        }
    }

    private void showProgressBar(Boolean state) {
        if (state) {
            pbMovie.setVisibility(View.VISIBLE);
        } else {
            pbMovie.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("state", state);
        super.onSaveInstanceState(outState);
    }

}

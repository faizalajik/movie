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
import com.example.film.adapter.TvShowAdapter;
import com.example.film.database.MovieDatabase;
import com.example.film.database.MovieDb;
import com.example.film.model.TvShow;
import com.example.film.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class TvShowFragment extends Fragment {

    private RecyclerView rv;
    private HomeViewModel homeViewModel;
    private String language, state;
    private ProgressBar progressBar;
    private TvShowAdapter tvShowAdapter;
    private MovieDatabase db;
    private SwipeRefreshLayout swipe;
    private ArrayList<TvShow> tvshows;

    public TvShowFragment() {
    }

    public TvShowFragment(String state) {
        this.state = state;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }
    @Override
    public void onResume() {
        super.onResume();
        addItem();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvShowAdapter = new TvShowAdapter();
        tvShowAdapter.notifyDataSetChanged();
        rv = view.findViewById(R.id.rv_tvshow);
        progressBar = view.findViewById(R.id.pbtvshow);
        swipe = view.findViewById(R.id.swipe_tvshow);
        rv.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        if (savedInstanceState != null && state == null) {
            state = savedInstanceState.getString("state");
            addItem();
        }
        tvShowAdapter.notifyDataSetChanged();
        language = getResources().getString(R.string.code_language);
        showProgressBar(true);
        addItem();
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                addItem();
                if (state.equals("fav") && tvshows.isEmpty()) {
                    swipe.setRefreshing(false);
                    Toast.makeText(getContext(), "Favorite Empty", Toast.LENGTH_SHORT).show();
                } else {
                    swipe.setRefreshing(false);
                }
            }
        });
    }

    public void addItem() {

        if (state.equals("home")) {
            homeViewModel = new ViewModelProvider(
                    this, new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);
            homeViewModel.setTvShow(language);
            homeViewModel.getDataTvShow().observe(this, new Observer<ArrayList<TvShow>>() {
                @Override
                public void onChanged(ArrayList<TvShow> tvShows) {
                    if (tvShows != null) {
                        rv.setAdapter(new TvShowAdapter(getActivity(), tvShows));
                        showProgressBar(false);
                    } else {
                        showProgressBar(true);
                    }
                }
            });
        } else {
            db = Room.databaseBuilder(getContext(), MovieDatabase.class, "favorite-database")
                    .allowMainThreadQueries().build();
            List<MovieDb> movieDbs = db.movieDao().getMovie("tvshow");
            tvshows = new ArrayList<>();

            tvshows.clear();
            if (movieDbs != null) {
                for (int i = 0; i < movieDbs.size(); i++) {
                    TvShow tvShow = new TvShow();
                    tvShow.setId(movieDbs.get(i).getId());
                    tvShow.setName(movieDbs.get(i).getTitle());
                    tvShow.setOverview(movieDbs.get(i).getOverview());
                    tvShow.setPosterPath(movieDbs.get(i).getPosterPath());
                    tvShow.setVoteAverage(movieDbs.get(i).getAverage());
                    tvShow.setFirstAirDate(movieDbs.get(i).getYear());
                    tvshows.add(tvShow);
                }
                rv.setAdapter(new TvShowAdapter(getActivity(), tvshows));
                showProgressBar(false);
            } else {
                showProgressBar(true);
            }
            tvShowAdapter.notifyDataSetChanged();
        }
    }

    public void showProgressBar(Boolean state) {
        if (state == true) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("state", state);
        super.onSaveInstanceState(outState);
    }
}

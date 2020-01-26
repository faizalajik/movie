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
import com.example.film.adapter.TvShowAdapter;
import com.example.film.model.TvShow;
import com.example.film.viewmodel.HomeViewModel;

import java.util.ArrayList;

public class TvShowFragment extends Fragment {

    private RecyclerView rv;
    private HomeViewModel homeViewModel;
    private String language;
    private ProgressBar progressBar;

    public TvShowFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TvShowAdapter tvShowAdapter = new TvShowAdapter();
        tvShowAdapter.notifyDataSetChanged();
        rv = view.findViewById(R.id.rv_tvshow);
        progressBar = view.findViewById(R.id.pbtvshow);
        rv.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);

        language = getResources().getString(R.string.code_language);
        homeViewModel = new ViewModelProvider(
                this, new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);
        homeViewModel.setTvShow(language);
        homeViewModel.getDataTvShow().observe(this, new Observer<ArrayList<TvShow>>() {
            @Override
            public void onChanged(ArrayList<TvShow> tvShows) {
                if (tvShows!=null) {
                    rv.setAdapter(new TvShowAdapter(getActivity(), tvShows));
                    showProgressBar(false);
                }
                else {
                    showProgressBar(true);
                }
            }
        });
    }

    public void showProgressBar (Boolean state){
        if(state == true){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }
}

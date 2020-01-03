package com.example.film;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.film.Model.TvShow;

import java.util.ArrayList;
import java.util.List;

public class TvShowFragment extends Fragment {

    private RecyclerView rv;
    private String[] titleTvShow, episodeTvShow, tvShowDescription, tvShowYear, tvShowGenre, tvShowRuntime;
    private TypedArray imgTvShow;
    private List<TvShow> dataTvShow;
    private TvShow tvShow;

    public TvShowFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tv_show, container, false);
        rv = v.findViewById(R.id.rv_tvshow);
        rv.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        initData();
        addData();
        rv.setAdapter(new TvShowAdapter(this.getActivity(), dataTvShow));

        return v;
    }

    public void initData() {
        titleTvShow = getResources().getStringArray(R.array.title_tvshow);
        tvShowDescription = getResources().getStringArray(R.array.tvshow_description);
        tvShowYear = getResources().getStringArray(R.array.year_tvshow);
        tvShowGenre = getResources().getStringArray(R.array.genre_tvshow);
        tvShowRuntime = getResources().getStringArray(R.array.runtime_tvshow);
        episodeTvShow = getResources().getStringArray(R.array.episode);
        imgTvShow = getResources().obtainTypedArray(R.array.image_tvshow);
    }

    public void addData() {
        dataTvShow = new ArrayList<>();
        for (int i = 0; i < titleTvShow.length; i++) {
            tvShow = new TvShow();
            tvShow.setTitle(titleTvShow[i]);
            tvShow.setDetail(tvShowDescription[i]);
            tvShow.setEpisode(episodeTvShow[i]);
            tvShow.setGenre(tvShowGenre[i]);
            tvShow.setYear(tvShowYear[i]);
            tvShow.setRuntime(tvShowRuntime[i]);
            tvShow.setImg(imgTvShow.getResourceId(i, -1));
            dataTvShow.add(tvShow);
        }
    }

}

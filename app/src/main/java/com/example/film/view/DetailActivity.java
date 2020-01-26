package com.example.film.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.film.R;
import com.example.film.model.Movie;
import com.example.film.model.TvShow;

public class DetailActivity extends AppCompatActivity {
    public static final String KODE = "DATA";
    public static final String STATE = "STATUS";

    private ImageView imgPoster, background;
    private TextView tvTitle, tvDescription, tvYear, tvRuntime;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        background = findViewById(R.id.background_photo);
        imgPoster = findViewById(R.id.foto_poster);
        tvRuntime = findViewById(R.id.movie_rated);
        tvYear = findViewById(R.id.movie_year);
        tvTitle = findViewById(R.id.movie_title);
        tvDescription = findViewById(R.id.description);
        progressBar = findViewById(R.id.pbdetail);

        showProgressBar(true);

        int state = getIntent().getIntExtra(STATE, 0);
        if (state == 1) {
            TvShow data = new TvShow();
            data = getIntent().getParcelableExtra(KODE);
            if (data != null){
                showProgressBar(false);
            }else {
                showProgressBar(true);
            }
            Glide.with(this).load("https://image.tmdb.org/t/p/w342" + data.getPosterPath()).into(imgPoster);
            tvYear.setText(data.getFirstAirDate());
            tvTitle.setText(data.getName());
            tvDescription.setText(data.getOverview());
            tvRuntime.setText(String.valueOf(data.getVoteAverage()));
            Glide.with(this).load("https://image.tmdb.org/t/p/w342" + data.getPosterPath()).into(background);

        } else {
            Movie data = new Movie();
            data = getIntent().getParcelableExtra(KODE);
            if (data != null){
                showProgressBar(false);
            }else {
                showProgressBar(true);
            }
            Glide.with(this).load("https://image.tmdb.org/t/p/w342" + data.getPosterPath()).into(imgPoster);
            tvYear.setText(data.getReleaseDate());
            tvRuntime.setText(String.valueOf(data.getVoteAverage()));
            tvTitle.setText(data.getTitle());
            tvDescription.setText(data.getOverview());
            Glide.with(this).load("https://image.tmdb.org/t/p/w342" + data.getPosterPath()).into(background);

        }

    }
    private void showProgressBar (Boolean state){
        if(state){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }
}


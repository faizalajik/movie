package com.example.film;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.film.Model.Movie;
import com.example.film.Model.TvShow;

public class DetailActivity extends AppCompatActivity {
    public static final String KODE = "DATA";
    public static final String STATE = "STATUS";

    private ImageView imgPoster, background;
    private TextView tvTitle, tvDescription, tvYear, tvRuntime, tvGenre, tvEpisode, dot, tvShowEpisode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        background = findViewById(R.id.background_photo);
        imgPoster = findViewById(R.id.foto_poster);
        tvRuntime = findViewById(R.id.movie_runtime);
        tvYear = findViewById(R.id.movie_year);
        tvGenre = findViewById(R.id.movie_genre);
        tvTitle = findViewById(R.id.movie_title);
        tvDescription = findViewById(R.id.description);
        tvEpisode = findViewById(R.id.episode);
        dot = findViewById(R.id.dot3);
        tvShowEpisode = findViewById(R.id.tvshow_episodes);


        int state = getIntent().getIntExtra(STATE, 0);
        if (state == 1) {
            TvShow data = new TvShow();
            data = getIntent().getParcelableExtra(KODE);
            imgPoster.setImageResource(data.getImg());
            tvYear.setText(data.getYear());
            tvRuntime.setText(data.getRuntime());
            tvGenre.setText(data.getGenre());
            tvTitle.setText(data.getTitle());
            tvDescription.setText(data.getDetail());
            tvShowEpisode.setText(data.getEpisode());
            background.setImageResource(data.getImg());


        } else {
            Movie data = new Movie();
            data = getIntent().getParcelableExtra(KODE);
            imgPoster.setImageResource(data.getImg());
            tvYear.setText(data.getYear());
            tvRuntime.setText(data.getRuntime());
            tvGenre.setText(data.getGenre());
            tvTitle.setText(data.gettitle());
            tvDescription.setText(data.getDetail());
            tvEpisode.setVisibility(View.GONE);
            dot.setVisibility(View.GONE);
            tvShowEpisode.setVisibility(View.GONE);
            background.setImageResource(data.getImg());
        }

    }

}

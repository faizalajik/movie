package com.example.film.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.film.R;
import com.example.film.adapter.MovieAdapter;
import com.example.film.database.MovieDatabase;
import com.example.film.database.MovieDb;
import com.example.film.model.Movie;
import com.example.film.model.TvShow;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    public static final String KODE = "DATA";
    public static final String STATE = "STATUS";

    private ImageView imgPoster, background;

    private ProgressBar progressBar;
    private boolean isFavorite = false;
    private int state;
    private Menu menuItem;
    private MovieDatabase db;
    private TvShow tvShow;
    private Movie movie;
    private List<MovieDb> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView tvTitle, tvDescription, tvYear, tvRuntime;
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
        db = Room.databaseBuilder(getApplicationContext(), MovieDatabase.class, "favorite-database")
                .allowMainThreadQueries().build();
        state = getIntent().getIntExtra(STATE, 0);
        String year;
        if (state == 1) {
            tvShow = new TvShow();
            tvShow = getIntent().getParcelableExtra(KODE);
            if (tvShow != null) {
                showProgressBar(false);
                getMovie(tvShow.getId());
            } else {
                showProgressBar(true);
            }
            Glide.with(this).load("https://image.tmdb.org/t/p/w342" + tvShow.getPosterPath()).into(imgPoster);
            year = tvShow.getFirstAirDate().substring(0, 4);
            tvYear.setText(year);
            tvTitle.setText(tvShow.getName());
            tvDescription.setText(tvShow.getOverview());
            tvRuntime.setText(String.valueOf(tvShow.getVoteAverage()));
            Glide.with(this).load("https://image.tmdb.org/t/p/w342" + tvShow.getPosterPath()).into(background);

        } else {
            movie = new Movie();
            movie = getIntent().getParcelableExtra(KODE);
            if (movie != null) {
                showProgressBar(false);
                getMovie(movie.getId());
            } else {
                showProgressBar(true);
            }
            Glide.with(this).load("https://image.tmdb.org/t/p/w342" + movie.getPosterPath()).into(imgPoster);
            year = movie.getReleaseDate().substring(0, 4);
            tvYear.setText(year);
            tvRuntime.setText(String.valueOf(movie.getVoteAverage()));
            tvTitle.setText(movie.getTitle());
            tvDescription.setText(movie.getOverview());
            Glide.with(this).load("https://image.tmdb.org/t/p/w342" + movie.getPosterPath()).into(background);

        }

    }

    private void showProgressBar(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fav_menu, menu);
        menuItem = menu;
        setFavorite();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.favorite) {
            if (!isFavorite) {
                isFavorite = true;
                if (state == 1) {
                    setFavorite();
                    saveTvShowFavorite(tvShow);
                    Toast.makeText(getApplicationContext(), "SAVE TO FAVORITE", Toast.LENGTH_SHORT).show();
                } else {
                    setFavorite();
                    saveMovieFavorite(movie);
                    Toast.makeText(getApplicationContext(), "SAVE TO FAVORITE", Toast.LENGTH_SHORT).show();
                }
            } else {
                deleteFavorite();
                isFavorite = false;
                setFavorite();
                Toast.makeText(getApplicationContext(), "Delete FAVORITE", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void setFavorite() {
        if (isFavorite) {
            menuItem.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_star_black_24dp));
        } else {
            menuItem.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_star_border_black_24dp));
        }
    }

    public void getMovie(final int id) {
        db = Room.databaseBuilder(getApplicationContext(), MovieDatabase.class, "favorite-database")
                .allowMainThreadQueries().build();
        movies = db.movieDao().getMovie(id);
        if (!movies.isEmpty()) {
            isFavorite = true;
        }
    }

    public void saveTvShowFavorite(TvShow data) {
        MovieDb movieDb = new MovieDb();
        movieDb.setId(data.getId());
        movieDb.setTitle(data.getName());
        movieDb.setAverage(data.getVoteAverage());
        movieDb.setOverview(data.getOverview());
        movieDb.setPosterPath(data.getPosterPath());
        movieDb.setYear(data.getFirstAirDate());
        movieDb.setCategory("tvshow");

        db.movieDao().insertMovie(movieDb);
    }

    public void saveMovieFavorite(Movie data) {
        MovieDb movieDb = new MovieDb();
        movieDb.setId(data.getId());
        movieDb.setTitle(data.getTitle());
        movieDb.setAverage(data.getVoteAverage());
        movieDb.setOverview(data.getOverview());
        movieDb.setPosterPath(data.getPosterPath());
        movieDb.setYear(data.getReleaseDate());
        movieDb.setCategory("movie");
        db.movieDao().insertMovie(movieDb);
    }

    public void deleteFavorite() {
        MovieDb movieDb = new MovieDb();
        if (state != 1) {
            movieDb.setCategory("movie");
            movieDb.setId(movie.getId());
            movieDb.setTitle(movie.getTitle());
            movieDb.setAverage(movie.getVoteAverage());
            movieDb.setOverview(movie.getOverview());
            movieDb.setPosterPath(movie.getPosterPath());

        } else {
            movieDb.setCategory("tvshow");
            movieDb.setId(tvShow.getId());
            movieDb.setTitle(tvShow.getName());
            movieDb.setAverage(tvShow.getVoteAverage());
            movieDb.setOverview(tvShow.getOverview());
            movieDb.setPosterPath(tvShow.getPosterPath());
        }

        db.movieDao().deleteMovie(movieDb);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}


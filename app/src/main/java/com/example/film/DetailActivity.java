package com.example.film;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    public static final String KODE = "DATA";

    private ImageView imgPoster;
    private TextView tvJudul, tvDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Film film = getIntent().getParcelableExtra(KODE);
        String j = film.getJudul();

        imgPoster = findViewById(R.id.foto_poster);
        tvJudul = findViewById(R.id.judul);
        tvDeskripsi = findViewById(R.id.deskripsi);

        imgPoster.setImageResource(film.getImg());
        tvJudul.setText(film.getJudul());
        tvDeskripsi.setText(film.getDetail());
    }
}

package com.example.film;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

import static com.example.film.DetailActivity.KODE;

public class MainActivity extends AppCompatActivity {

    private FilmAdapter adapter;
    private String[] judul;
    private String[] deskripsi;
    private TypedArray img;
    private List<Film> dataFilm;
    private Film film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list_item);
        adapter = new FilmAdapter(this);
        listView.setAdapter(adapter);

        judul = getResources().getStringArray(R.array.judul);
        deskripsi = getResources().getStringArray(R.array.Deskripsi);
        img = getResources().obtainTypedArray(R.array.image);

        tambahItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Film film = dataFilm.get(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(KODE, film);
                startActivity(intent);

            }
        });
    }

    public void tambahItem() {
        dataFilm = new ArrayList<>();
        for (int i = 0; i < judul.length; i++) {
            film = new Film();
            film.setJudul(judul[i]);
            film.setDetail(deskripsi[i]);
            film.setImg(img.getResourceId(i, -1));
            dataFilm.add(film);
        }
        adapter.setFilm(dataFilm);
    }
}

package com.example.film;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends BaseAdapter {
    private Context context;
    private List<Movie> film = new ArrayList<>();

    public void setFilm(List<Movie> film) {
        this.film = film;
    }

    public FilmAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return film.size();
    }

    @Override
    public Object getItem(int position) {
        return film.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View itemView = view;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        }

        ViewHolder viewHolder = new ViewHolder(itemView);

        Movie film = (Movie) getItem(position);
        viewHolder.bind(film);
        return itemView;
    }

    private class ViewHolder {
        private TextView tvJudul;
        private ImageView img;

        ViewHolder(View view) {
            tvJudul = view.findViewById(R.id.judul_film);
            img = view.findViewById(R.id.img_film);
        }

        void bind(Movie film) {
            tvJudul.setText(film.getJudul());
            img.setImageResource(film.getImg());
        }
    }
}

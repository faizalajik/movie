package com.example.film.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.film.R;
import com.example.film.model.Movie;
import com.example.film.view.DetailActivity;

import java.util.List;
import static com.example.film.view.DetailActivity.KODE;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private Context context;
    private List<Movie> movieList;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }
    public MovieAdapter(){

    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View v;
        v = layoutInflater.inflate(R.layout.cardview_movie, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, final int position) {
        holder.tvTitle.setText(movieList.get(position).getTitle());
        Glide.with(context).load("https://image.tmdb.org/t/p/w342"+movieList.get(position).getPosterPath()).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movie = new Movie();
                movie = movieList.get(position);
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(KODE, movie);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView img;

        public ViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.movie_title);
            img = v.findViewById(R.id.img_movie);
        }
    }
}

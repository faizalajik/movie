package com.example.film;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.film.Model.TvShow;
import com.example.film.View.DetailActivity;

import java.util.List;
import static com.example.film.View.DetailActivity.KODE;
import static com.example.film.View.DetailActivity.STATE;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {

    private Context context;
    private List<TvShow> tvShowsList;

    public TvShowAdapter(Context context, List<TvShow> tvShowsList) {
        this.context = context;
        this.tvShowsList = tvShowsList;
    }

    @NonNull
    @Override
    public TvShowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View v;
        v = layoutInflater.inflate(R.layout.cardview_tvshow, parent, false);

        return new TvShowAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.ViewHolder holder, final int position) {
        holder.tvTitle.setText(tvShowsList.get(position).getTitle());
        holder.img.setImageResource(tvShowsList.get(position).getImg());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TvShow tvShow = new TvShow();
                tvShow = tvShowsList.get(position);
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(KODE, tvShow);
                intent.putExtra(STATE, 1);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvShowsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvshow_title);
            img = itemView.findViewById(R.id.img_tvshow);
        }
    }
}

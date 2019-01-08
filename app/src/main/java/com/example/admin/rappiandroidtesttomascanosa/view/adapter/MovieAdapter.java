package com.example.admin.rappiandroidtesttomascanosa.View.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.example.admin.rappiandroidtesttomascanosa.model.Movie;
import com.example.admin.rappiandroidtesttomascanosa.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter {

    private List<Movie> movieList;
    private SelectionNofitier selectionNofitier;
    private Context context;

    public MovieAdapter(List<Movie> movieList, SelectionNofitier selectionNofitier) {
        this.movieList = movieList;
        this.selectionNofitier = selectionNofitier;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View cellView = layoutInflater.inflate(R.layout.mini_poster_cell, parent, false);
        MovieViewHolder serieViewHolder = new MovieViewHolder(cellView);

        return serieViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
        movieViewHolder.loadMoviePoster(movie);
    }

    public void loadMovies(List<Movie> moviesToLoad) {
        movieList.addAll(moviesToLoad);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    private class MovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView moviePoster;

        public MovieViewHolder(View itemView) {
            super(itemView);

            moviePoster = itemView.findViewById(R.id.celda_mini_poster_image);

            moviePoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectionNofitier.openMovieDetail(movieList, getAdapterPosition());
                }
            });

        }

        public void loadMoviePoster(Movie movie) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w300" +
                    movie.getPoster_path()).into(moviePoster);
        }

    }

    public interface SelectionNofitier {
        void openMovieDetail(List<Movie> movies, Integer moviePosition);
    }

}
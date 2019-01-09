package com.example.admin.rappiandroidtesttomascanosa.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.admin.rappiandroidtesttomascanosa.controller.TMDBMovieController;
import com.example.admin.rappiandroidtesttomascanosa.model.Movie;
import com.example.admin.rappiandroidtesttomascanosa.view.adapter.DataToMovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class MoviesRecyclerViewSetter {

    private DataToMovieAdapter movieAdapter;
    private List<Movie> movieList;
    private TMDBMovieController movieController;
    private Integer pageNumber;
    private String movieControllerCategory;
    private String language;
    private Context context;
    private String movieID;

    public MoviesRecyclerViewSetter(Context context, String language, DataToMovieAdapter.SelectionNofitier selectionNofitier) {
        pageNumber = 1;
        movieController = new TMDBMovieController();
        movieList = new ArrayList<>();
        this.context = context;
        this.language = language;
        movieAdapter = new DataToMovieAdapter(movieList, selectionNofitier);
    }

    public void setCategorizedMoviesRecyclerView(RecyclerView moviesRecyclerView, String movieControllerCategory, int linearLayoutManagerOrientation) {
        this.movieControllerCategory = movieControllerCategory;
        getCategorizedMoviesFromAPI();
        moviesRecyclerView.setHasFixedSize(true);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(context, linearLayoutManagerOrientation, false));
        moviesRecyclerView.setAdapter(movieAdapter);

        moviesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int total = layoutManager.getItemCount();
                int currentLastItem = layoutManager.findLastVisibleItemPosition();
                if (currentLastItem == total - 6) {
                    getMoreCategorizedMovies();
                }
            }
        });
    }

    private void getCategorizedMoviesFromAPI() {
        movieController.getCategorizedMovies(movieControllerCategory, pageNumber, language, new ResultListener<List<Movie>>() {
            @Override
            public void finish(List<Movie> result) {
                movieList = result;
                movieAdapter.loadMovies(movieList);
            }
        });
    }

    private void getMoreCategorizedMovies() {
        pageNumber++;
        getCategorizedMoviesFromAPI();
    }

    public void setSimilarMoviesRecyclerView(RecyclerView moviesRecyclerView, String movieID, int linearLayoutManagerOrientation) {
        this.movieID = movieID;
        getSimilarMoviesFromAPI();
        moviesRecyclerView.setHasFixedSize(true);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(context, linearLayoutManagerOrientation, false));
        moviesRecyclerView.setAdapter(movieAdapter);

        moviesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int total = layoutManager.getItemCount();
                int currentLastItem = layoutManager.findLastVisibleItemPosition();
                if (currentLastItem == total - 6) {
                    getMoreSimilarMovies();
                }
            }
        });
    }

    private void getSimilarMoviesFromAPI() {
        movieController.getSimilarMovies(movieID, pageNumber, language, new ResultListener<List<Movie>>() {
            @Override
            public void finish(List<Movie> result) {
                movieList = result;
                movieAdapter.loadMovies(movieList);
            }
        });
    }

    private void getMoreSimilarMovies() {
        pageNumber++;
        getSimilarMoviesFromAPI();
    }

}

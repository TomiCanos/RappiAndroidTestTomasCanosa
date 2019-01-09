package com.example.admin.rappiandroidtesttomascanosa.controller;

import com.example.admin.rappiandroidtesttomascanosa.model.Movie;
import com.example.admin.rappiandroidtesttomascanosa.utils.ResultListener;
import com.example.admin.rappiandroidtesttomascanosa.retrofit.TMDBMovieDAO;

import java.util.List;

public class TMDBMovieController {

    public static final String POPULAR = "popular";
    public static final String TOP_RATED = "top_rated";
    public static final String UPCOMING = "upcoming";
    private TMDBMovieDAO movieDAO;
    private boolean internetWorks;

    public TMDBMovieController() {
        internetWorks = true;
        movieDAO = new TMDBMovieDAO();
    }


    public void getCategorizedMovies(String category, Integer page, String language,
                                     final ResultListener<List<Movie>> resultListener) {
        if (internetWorks) {
            movieDAO.getCategorizedMovies(category, language, page, new ResultListener<List<Movie>>() {
                @Override
                public void finish(List<Movie> result) {
                    resultListener.finish(result);
                }
            });
        }

    }

    public void getSimilarMovies(String movieID, Integer page, String language,
                                 final ResultListener<List<Movie>> resultListener) {
        if (internetWorks) {
            movieDAO.getSimilarMovies(movieID, language, page, new ResultListener<List<Movie>>() {
                @Override
                public void finish(List<Movie> result) {
                    resultListener.finish(result);
                }
            });
        }

    }

}

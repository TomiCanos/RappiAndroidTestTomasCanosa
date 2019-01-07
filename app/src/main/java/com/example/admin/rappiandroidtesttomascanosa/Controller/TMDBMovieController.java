package com.example.admin.rappiandroidtesttomascanosa.Controller;

import android.icu.text.LocaleDisplayNames;
import android.icu.util.LocaleData;
import android.os.LocaleList;

import com.example.admin.rappiandroidtesttomascanosa.Model.Movie;
import com.example.admin.rappiandroidtesttomascanosa.Model.MoviesContainer;
import com.example.admin.rappiandroidtesttomascanosa.Retrofit.ResultListener;
import com.example.admin.rappiandroidtesttomascanosa.Retrofit.TMDBMovieDAO;

import java.util.List;
import java.util.Locale;

public class TMDBMovieController {

    private static final String POPULAR = "popular";
    private static final String TOP_RATED = "top_rated";
    private static final String UPCOMING = "upcoming";
    private TMDBMovieDAO movieDAO;
    private boolean internetWorks;

    public TMDBMovieController() {
        internetWorks = true;
        movieDAO = new TMDBMovieDAO();
    }

    public void getPopularMovies(Integer page, Locale language, final ResultListener<List<Movie>> resultListener) {
        getCategorizedMovies(POPULAR, page, language, resultListener);
    }

    public void getTopRatedMovies(Integer page, Locale language, final ResultListener<List<Movie>> resultListener) {
        getCategorizedMovies(TOP_RATED, page, language, resultListener);
    }

    public void getUpcomingMovies(Integer page, Locale language, final ResultListener<List<Movie>> resultListener) {
        getCategorizedMovies(UPCOMING, page, language, resultListener);
    }

    private void getCategorizedMovies(String category, Integer page, Locale language,
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

}

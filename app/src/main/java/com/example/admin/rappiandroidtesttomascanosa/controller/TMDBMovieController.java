package com.example.admin.rappiandroidtesttomascanosa.controller;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.example.admin.rappiandroidtesttomascanosa.dataLoader.room.MovieRoomDatabase;
import com.example.admin.rappiandroidtesttomascanosa.dataLoader.room.RoomMovieDAO;
import com.example.admin.rappiandroidtesttomascanosa.model.Movie;
import com.example.admin.rappiandroidtesttomascanosa.utils.ResultListener;
import com.example.admin.rappiandroidtesttomascanosa.dataLoader.api.TMDBMovieDAO;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class TMDBMovieController {

    public static final String POPULAR = "popular";
    public static final String TOP_RATED = "top_rated";
    public static final String UPCOMING = "upcoming";
    private TMDBMovieDAO movieDAO;
    private RoomMovieDAO roomMovieDAO;
    private boolean firstTime;
    private Context context;

    public TMDBMovieController(Context context) {
        this.context = context;
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(context);
        roomMovieDAO = db.movieDAO();
        firstTime = true;
        movieDAO = new TMDBMovieDAO();
    }

    public void insertMovieIntoRoomDatabase(Movie movie){
        new insertAsyncTask(roomMovieDAO).execute(movie);
    }

    public void getCategorizedMovies(String category, Integer page, String language,
                                     final ResultListener<List<Movie>> resultListener) {
            movieDAO.getCategorizedMovies(category, language, page, new ResultListener<List<Movie>>() {
                @Override
                public void finish(List<Movie> result) {
                    if (result != null) {
                        resultListener.finish(result);
                        if (firstTime) {
                            for (Movie movie : result) {
                                insertMovieIntoRoomDatabase(movie);
                            }
                            firstTime = false;
                        }
                    } else {
                        getMoviesFromRoom(resultListener);
                    }
                }
            });
    }

    public void getSimilarMovies(String movieID, Integer page, String language,
                                 final ResultListener<List<Movie>> resultListener) {

            movieDAO.getSimilarMovies(movieID, language, page, new ResultListener<List<Movie>>() {
                @Override
                public void finish(List<Movie> result) {
                    resultListener.finish(result);
                }
            });

    }

    public void getMoviesFromRoom(final ResultListener<List<Movie>> resultListener) {
        roomMovieDAO.getAllMovies().observe((LifecycleOwner) context, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                resultListener.finish(movies);
            }
        });
    }

    private static class insertAsyncTask extends AsyncTask<Movie, Void, Void> {

        private RoomMovieDAO asyncTaskDao;

        insertAsyncTask(RoomMovieDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie... params) {
            asyncTaskDao.addMovie(params[0]);
            return null;
        }
    }

}

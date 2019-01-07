package com.example.admin.rappiandroidtesttomascanosa.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.rappiandroidtesttomascanosa.Controller.TMDBMovieController;
import com.example.admin.rappiandroidtesttomascanosa.Model.Movie;
import com.example.admin.rappiandroidtesttomascanosa.R;
import com.example.admin.rappiandroidtesttomascanosa.Retrofit.ResultListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private List<Movie> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Locale current = getResources().getConfiguration().locale;
        results = new ArrayList<>();

        TMDBMovieController tmdbMovieController = new TMDBMovieController();

        tmdbMovieController.getPopularMovies(1, current, new ResultListener<List<Movie>>() {
            @Override
            public void finish(List<Movie> result) {
                results = result;
            }
        });

    }
}

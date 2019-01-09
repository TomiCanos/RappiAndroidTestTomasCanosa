package com.example.admin.rappiandroidtesttomascanosa.view;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.rappiandroidtesttomascanosa.R;
import com.example.admin.rappiandroidtesttomascanosa.model.Movie;
import com.example.admin.rappiandroidtesttomascanosa.utils.CubeTransformer;
import com.example.admin.rappiandroidtesttomascanosa.view.adapter.MovieToFragmentAdapter;

import java.util.List;


public class MovieDetailActivity extends AppCompatActivity {
    public static final String MOVIE_LIST_ID = "MOVIE_LIST_ID";
    public static final String POSITION_ID = "POSITION_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        List<Movie> movies = (List<Movie>) bundle.getSerializable(MOVIE_LIST_ID);
        int moviePosition = bundle.getInt(POSITION_ID);
        ViewPager viewPager = findViewById(R.id.movie_detail_activity_view_pager);
        MovieToFragmentAdapter movieAdapterViewPager = new MovieToFragmentAdapter(getSupportFragmentManager(), movies);
        viewPager.setAdapter(movieAdapterViewPager);
        viewPager.setCurrentItem(moviePosition);
        viewPager.setPageTransformer(true, new CubeTransformer());
    }
}

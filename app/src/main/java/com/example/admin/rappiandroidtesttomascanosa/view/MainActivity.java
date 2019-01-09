package com.example.admin.rappiandroidtesttomascanosa.view;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.admin.rappiandroidtesttomascanosa.R;
import com.example.admin.rappiandroidtesttomascanosa.model.Movie;

import java.io.Serializable;
import java.util.List;


public class MainActivity extends AppCompatActivity implements HomeFragment.SelectionNofitier {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = new Bundle();
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_activity_fragment_containter, homeFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void openMovieDetail(List<Movie> movies, Integer moviePosition) {
        Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MovieDetailActivity.MOVIE_LIST_ID, (Serializable) movies);
        bundle.putInt(MovieDetailActivity.POSITION_ID, moviePosition);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}

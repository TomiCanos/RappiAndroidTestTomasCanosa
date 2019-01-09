package com.example.admin.rappiandroidtesttomascanosa.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.admin.rappiandroidtesttomascanosa.model.Movie;
import com.example.admin.rappiandroidtesttomascanosa.view.MovieDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class MovieToFragmentAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> moviesAsFragments;

    public MovieToFragmentAdapter(FragmentManager fm, List<Movie> movieList) {
        super(fm);
        moviesAsFragments = new ArrayList<>();

        for (Movie movie : movieList) {
            MovieDetailFragment movieDetailFragment = MovieDetailFragment.movieAsFragmentConvertor(movie);
            moviesAsFragments.add(movieDetailFragment);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return moviesAsFragments.get(position);
    }

    @Override
    public int getCount() {
        return moviesAsFragments.size();
    }

}
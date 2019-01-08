package com.example.admin.rappiandroidtesttomascanosa.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.rappiandroidtesttomascanosa.R;
import com.example.admin.rappiandroidtesttomascanosa.model.Movie;

public class MovieDetailFragment extends Fragment {

    private static final String MOVIE_ID = "MOVIE_LIST_ID";

    private ImageView image;
    private TextView title;
    private TextView overview;
    private Movie movie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        image = view.findViewById(R.id.movie_fragment_detail_image);
        title  = view.findViewById(R.id.movie_fragment_detail_title);
        overview = view.findViewById(R.id.movie_fragment_detail_overview);

        Bundle bundle = getArguments();

        movie = (Movie) bundle.getSerializable(MOVIE_ID);

        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());

        return view;
    }

    public static MovieDetailFragment movieAsFragmentConvertor(Movie movie) {

        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(MOVIE_ID, movie);
        movieDetailFragment.setArguments(bundle);
        return movieDetailFragment;
    }

}

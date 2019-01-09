package com.example.admin.rappiandroidtesttomascanosa.view;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.rappiandroidtesttomascanosa.R;
import com.example.admin.rappiandroidtesttomascanosa.model.Movie;
import com.example.admin.rappiandroidtesttomascanosa.utils.MoviesRecyclerViewSetter;
import com.example.admin.rappiandroidtesttomascanosa.view.adapter.DataToMovieAdapter;

import java.util.List;

public class MovieDetailFragment extends Fragment {

    private static final String MOVIE_ID = "MOVIE_ID";

    private ImageView image;
    private TextView title;
    private TextView overview;
    private TextView voteAverage;
    private TextView releaseDate;
    private Movie movie;
    private String language;
    private RecyclerView similarMoviesRecyclerView;
    private MoviesRecyclerViewSetter similarMoviesGridViewSetter ;
    private SelectionNofitier selectionNofitier;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        image = view.findViewById(R.id.movie_detail_fragment_image);
        title = view.findViewById(R.id.movie_detail_fragment_title);
        overview = view.findViewById(R.id.movie_detail_fragment_overview);
        voteAverage = view.findViewById(R.id.movie_detail_fragment_vote_average);
        releaseDate = view.findViewById(R.id.movie_detail_fragment_release_date);

        Bundle bundle = getArguments();

        movie = (Movie) bundle.getSerializable(MOVIE_ID);
        language = getResources().getConfiguration().locale.toString().replace("_", "-");

        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        releaseDate.setText(movie.getRelease_date());
        String voteAverageAsString = movie.getVote_average();
        voteAverage.setText(voteAverageAsString);
        changeStringColorByVoteAverage(voteAverageAsString);

        Glide.with(this).load("https://image.tmdb.org/t/p/w300" + movie.getBackdrop_path()).into(image);

        similarMoviesRecyclerView = view.findViewById(R.id.movie_detail_fragment_recycler_view_similar_movies);
        similarMoviesGridViewSetter = new MoviesRecyclerViewSetter(getContext(), language, new DataToMovieAdapter.SelectionNofitier() {
            @Override
            public void openMovieDetail(List<Movie> movies, Integer moviePosition) {
                selectionNofitier.openMovieDetail(movies, moviePosition);
            }
        });

        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        float spanCount = dpWidth / 186;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), Math.round(spanCount));
        similarMoviesGridViewSetter.setSimilarMoviesRecyclerView(similarMoviesRecyclerView, movie.getId(), gridLayoutManager);

        return view;
    }

    private String changeStringColorByVoteAverage(String voteAverageAsString) {
        Double voteAverageAsDouble = Double.valueOf(voteAverageAsString);
        if (voteAverageAsDouble < 7.0 && voteAverageAsDouble > 4) {
            voteAverage.setTextColor(Color.YELLOW);
        } else if (voteAverageAsDouble < 4) {
            voteAverage.setTextColor(Color.RED);
        }
        return voteAverageAsString;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        selectionNofitier = (SelectionNofitier) context;
    }

    public static MovieDetailFragment movieAsFragmentConvertor(Movie movie) {

        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(MOVIE_ID, movie);
        movieDetailFragment.setArguments(bundle);
        return movieDetailFragment;
    }

    public interface SelectionNofitier {
        void openMovieDetail(List<Movie> movies, Integer moviePosition);
    }
}

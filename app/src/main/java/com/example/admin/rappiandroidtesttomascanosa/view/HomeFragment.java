package com.example.admin.rappiandroidtesttomascanosa.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.rappiandroidtesttomascanosa.controller.DataAsMovieController;
import com.example.admin.rappiandroidtesttomascanosa.model.Movie;
import com.example.admin.rappiandroidtesttomascanosa.R;
import com.example.admin.rappiandroidtesttomascanosa.utils.MoviesRecyclerViewSetter;
import com.example.admin.rappiandroidtesttomascanosa.view.adapter.DataToMovieAdapter;

import java.util.List;

public class HomeFragment extends Fragment {
    private MoviesRecyclerViewSetter horizontalPopularMoviesRecyclerViewSetter;
    private MoviesRecyclerViewSetter horizontalTopRatedMoviesRecyclerViewSetter;
    private MoviesRecyclerViewSetter horizontalUpcomingMoviesRecyclerViewSetter;
    private RecyclerView popularMoviesRecyclerView;
    private RecyclerView topRatedMoviesRecyclerView;
    private RecyclerView upcomingMoviesRecyclerView;
    private SelectionNofitier selectionNofitier;
    private String language;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        language = getResources().getConfiguration().locale.toString().replace("_", "-");

        if (view != null) {
            return view;
        }

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        horizontalPopularMoviesRecyclerViewSetter = new MoviesRecyclerViewSetter(getContext(), language, new DataToMovieAdapter.SelectionNofitier() {
            @Override
            public void openMovieDetail(List<Movie> movies, Integer moviePosition) {
                selectionNofitier.openMovieDetail(movies, moviePosition);
            }
        });

        horizontalTopRatedMoviesRecyclerViewSetter = new MoviesRecyclerViewSetter(getContext(), language, new DataToMovieAdapter.SelectionNofitier() {
            @Override
            public void openMovieDetail(List<Movie> movies, Integer moviePosition) {
                selectionNofitier.openMovieDetail(movies, moviePosition);
            }
        });
        horizontalUpcomingMoviesRecyclerViewSetter = new MoviesRecyclerViewSetter(getContext(), language, new DataToMovieAdapter.SelectionNofitier() {
            @Override
            public void openMovieDetail(List<Movie> movies, Integer moviePosition) {
                selectionNofitier.openMovieDetail(movies, moviePosition);
            }
        });

        popularMoviesRecyclerView = view.findViewById(R.id.home_fragment_recycler_popular);
        topRatedMoviesRecyclerView = view.findViewById(R.id.home_fragment_recycler_top_rated);
        upcomingMoviesRecyclerView = view.findViewById(R.id.home_fragment_recycler_upcoming);

        horizontalPopularMoviesRecyclerViewSetter.setCategorizedMoviesRecyclerView(popularMoviesRecyclerView,
                DataAsMovieController.POPULAR, new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        horizontalTopRatedMoviesRecyclerViewSetter.setCategorizedMoviesRecyclerView(topRatedMoviesRecyclerView,
                DataAsMovieController.TOP_RATED, new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        horizontalUpcomingMoviesRecyclerViewSetter.setCategorizedMoviesRecyclerView(upcomingMoviesRecyclerView,
                DataAsMovieController.UPCOMING, new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        selectionNofitier = (SelectionNofitier) context;
    }

    @Override
    public void onStop() {
        view = getView();
        super.onStop();
    }

    public interface SelectionNofitier {
        void openMovieDetail(List<Movie> movies, Integer moviePosition);
    }
}


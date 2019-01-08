package com.example.admin.rappiandroidtesttomascanosa.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.rappiandroidtesttomascanosa.controller.TMDBMovieController;
import com.example.admin.rappiandroidtesttomascanosa.model.Movie;
import com.example.admin.rappiandroidtesttomascanosa.R;
import com.example.admin.rappiandroidtesttomascanosa.utils.ResultListener;
import com.example.admin.rappiandroidtesttomascanosa.view.adapter.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    public static final String LANGUAGE = "language";
    private String language;
    private HorizontalMoviesRecyclerViewSetter horizontalPopularMoviesRecyclerViewSetter;
    private HorizontalMoviesRecyclerViewSetter horizontalTopRatedMoviesRecyclerViewSetter;
    private HorizontalMoviesRecyclerViewSetter horizontalUpcomingMoviesRecyclerViewSetter;
    private RecyclerView popularMoviesRecyclerView;
    private RecyclerView topRatedMoviesRecyclerView;
    private RecyclerView upcomingMoviesRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        language = getArguments().getString(LANGUAGE);

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        horizontalPopularMoviesRecyclerViewSetter = new HorizontalMoviesRecyclerViewSetter();
        horizontalTopRatedMoviesRecyclerViewSetter = new HorizontalMoviesRecyclerViewSetter();
        horizontalUpcomingMoviesRecyclerViewSetter = new HorizontalMoviesRecyclerViewSetter();

        popularMoviesRecyclerView = view.findViewById(R.id.fragment_home_recycler_popular);
        topRatedMoviesRecyclerView = view.findViewById(R.id.fragment_home_recycler_top_rated);
        upcomingMoviesRecyclerView = view.findViewById(R.id.fragment_home_recycler_upcoming);

        horizontalPopularMoviesRecyclerViewSetter.setMoviesRecyclerView(popularMoviesRecyclerView, TMDBMovieController.POPULAR);
        horizontalTopRatedMoviesRecyclerViewSetter.setMoviesRecyclerView(topRatedMoviesRecyclerView, TMDBMovieController.TOP_RATED);
        horizontalUpcomingMoviesRecyclerViewSetter.setMoviesRecyclerView(upcomingMoviesRecyclerView, TMDBMovieController.UPCOMING);


        return view;
    }


    private class HorizontalMoviesRecyclerViewSetter {
        private MovieAdapter movieAdapter;
        private List<Movie> movieList;
        private TMDBMovieController movieController;
        private Integer pageNumber;
        private String movieControllerCategory;

        public HorizontalMoviesRecyclerViewSetter() {
            pageNumber = 1;
            movieController = new TMDBMovieController();
            movieList = new ArrayList<>();
            movieAdapter = new MovieAdapter(movieList, new MovieAdapter.SelectionNofitier() {
                @Override
                public void openMovieDetail(List<Movie> movies, Integer moviePosition) {
                    return;
                }
            });
        }

        public void setMoviesRecyclerView(RecyclerView moviesRecyclerView, String movieControllerCategory) {
            this.movieControllerCategory = movieControllerCategory;
            getMoviesFromAPI();
            moviesRecyclerView.setHasFixedSize(true);
            moviesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            moviesRecyclerView.setAdapter(movieAdapter);

            moviesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int total = layoutManager.getItemCount();
                    int currentLastItem = layoutManager.findLastVisibleItemPosition();
                    if (currentLastItem == total - 6) {
                        getMoreMovies();
                    }
                }
            });
        }

        private void getMoviesFromAPI() {
            movieController.getCategorizedMovies(movieControllerCategory, pageNumber, language, new ResultListener<List<Movie>>() {
                @Override
                public void finish(List<Movie> result) {
                    movieList = result;
                    movieAdapter.loadMovies(movieList);
                }
            });
        }

        private void getMoreMovies() {
            pageNumber++;
            getMoviesFromAPI();
        }
    }

}


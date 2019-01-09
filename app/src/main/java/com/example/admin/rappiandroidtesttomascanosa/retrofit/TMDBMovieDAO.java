package com.example.admin.rappiandroidtesttomascanosa.retrofit;

import android.util.Log;

import com.example.admin.rappiandroidtesttomascanosa.model.Movie;
import com.example.admin.rappiandroidtesttomascanosa.model.MoviesContainer;
import com.example.admin.rappiandroidtesttomascanosa.utils.ResultListener;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TMDBMovieDAO {

    private static final String BASE_URL = "https://api.themoviedb.org/";
    private static final String API_KEY = "d076383614b3a91c60e0162f9e633fac";
    private static final String ACCES_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
            ".eyJhdWQiOiJkMDc2MzgzNjE0YjNhOTFjNjBlMDE2MmY5ZTYzM2ZhYyIsInN1YiI6IjVjMmE2YjJl" +
            "MGUwYTI2NzRmOGQzMTdmZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.3Bmmf" +
            "F5UBUUFZD1-VcAgKntASUVaJzQcHq4EoCM6JtI";
    private Retrofit retrofit;
    private MovieService service;

    public TMDBMovieDAO() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build()).build();

        service = retrofit.create(MovieService.class);

    }

    public void getCategorizedMovies(String category, String language, Integer page, final ResultListener<List<Movie>> resultListener) {

        service.getCategorizedMovies(category, API_KEY, language, page).enqueue(new Callback<MoviesContainer>() {
            @Override
            public void onResponse(Call<MoviesContainer> call, Response<MoviesContainer> response) {
                MoviesContainer moviesContainer = response.body();
                List<Movie> resultsAsMovieList = moviesContainer.getResults();
                resultListener.finish(resultsAsMovieList);
            }

            @Override
            public void onFailure(Call<MoviesContainer> call, Throwable t) {
                Log.e("retrofit", "failed");
            }
        });

    }

    public void getSimilarMovies(String movieID, String language, Integer page, final ResultListener<List<Movie>> resultListener) {

        service.getSimilarMovies(movieID, API_KEY, language, page).enqueue(new Callback<MoviesContainer>() {
            @Override
            public void onResponse(Call<MoviesContainer> call, Response<MoviesContainer> response) {
                MoviesContainer moviesContainer = response.body();
                List<Movie> resultsAsmovieList = moviesContainer.getResults();
                resultListener.finish(resultsAsmovieList);
            }

            @Override
            public void onFailure(Call<MoviesContainer> call, Throwable t) {
                Log.e("retrofit", "failed");
            }
        });
    }

}

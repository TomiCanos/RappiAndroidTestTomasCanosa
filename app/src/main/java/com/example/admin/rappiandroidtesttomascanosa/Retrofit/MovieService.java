package com.example.admin.rappiandroidtesttomascanosa.Retrofit;

import com.example.admin.rappiandroidtesttomascanosa.Model.MoviesContainer;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("/3/movie/{category}")
    Call<MoviesContainer> getCategorizedMovies(@Path ("category") String category, @Query("api_key") String apiKey,
                                               @Query("language") Locale language, @Query("page") Integer page);


}

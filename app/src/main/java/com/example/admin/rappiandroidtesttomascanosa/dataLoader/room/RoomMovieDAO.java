package com.example.admin.rappiandroidtesttomascanosa.dataLoader.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.admin.rappiandroidtesttomascanosa.model.Movie;

import java.util.List;

@Dao
public interface RoomMovieDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addMovies(List<Movie> movieList);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addMovie(Movie movie);

    @Query("DELETE FROM Movie")
    void deleteAll();

    @Query("SELECT * FROM Movie WHERE title = :movieTitle")
    List<Movie> getMobieByTitle(String movieTitle);

    @Query("SELECT * FROM Movie")
    LiveData<List<Movie>> getAllMovies();

    @Query("SELECT * FROM Movie WHERE category = :movieCategory")
    LiveData<List<Movie>> getAllMoviesByCategory(String movieCategory);

}

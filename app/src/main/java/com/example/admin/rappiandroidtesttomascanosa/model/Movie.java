package com.example.admin.rappiandroidtesttomascanosa.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity
public class Movie implements Serializable {
    @Ignore
    private String id;
    @PrimaryKey
    @NonNull
    private String title;
    private String poster_path;
    @Ignore
    private String backdrop_path;
    private String overview;
    @Ignore
    private String vote_average;
    @Ignore
    private String release_date;

    public Movie(){}

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getId() {
        return id;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}

package com.example.admin.rappiandroidtesttomascanosa.model;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private String poster_path;
    private String backdrop_path;
    private String overview;
    private String vote_average;
    private String id;
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
}

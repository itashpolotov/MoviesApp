package com.example.moviesapp;

import java.util.ArrayList;

public class Results {
    float popularity;
    float vote_count;
    boolean video;
    String poster_path;
    int id;
    boolean adult;
    String backdrop_path;
    String original_language;
    String original_title;
    ArrayList<Integer> genre_ids;
    String title;
    float vote_average;
    String overview;
    String release_date;

    public float getPopularity() {
        return popularity;
    }

    public float getVote_count() {
        return vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public String getPoster_path() {
        String path=Utils.imageBaseUrl+poster_path;
        return path;
    }

    public int getId() {
        return id;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getTitle() {
        return title;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }
}

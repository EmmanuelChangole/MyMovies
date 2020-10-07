package com.echangole.mymovies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieList {



    @SerializedName("original_title")
    @Expose
    private String original_name;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("vote_average")
    @Expose
    private float vote_average;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("poster_path")
    @Expose
    private String poster_path;
    @SerializedName("media_type")
    @Expose()
    private String media_type;

    public MovieList(String original_name, int id, float vote_average, String overview, String poster_path, String media_type) {
        this.original_name = original_name;
        this.id = id;
        this.vote_average = vote_average;
        this.overview = overview;
        this.poster_path = poster_path;
        this.media_type = media_type;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public int getId() {
        return id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getMedia_type() {
        return media_type;
    }

    @Override
    public String toString() {
        return "MovieList{" +
                "original_name='" + original_name + '\'' +
                ", id=" + id +
                ", vote_average=" + vote_average +
                ", overview='" + overview + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", media_type='" + media_type + '\'' +
                '}';
    }
}
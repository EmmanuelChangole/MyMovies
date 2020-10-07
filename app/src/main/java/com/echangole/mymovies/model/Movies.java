package com.echangole.mymovies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movies
{
    @SerializedName("popularity")
    @Expose
     private float popularity;
    @SerializedName("title")
    @Expose
     private String title;
    @SerializedName("vote_average")
    @Expose
     private float vote_average;
    @SerializedName("overview")
    @Expose
     private String overview;
    @SerializedName("release_date")
    @Expose
     private String release_date;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("poster_path")
    @Expose
   private String poster_path;




    public Movies(int id,float popularity, String title, float vote_average, String overview, String release_date,String poster_path)
    {
        this.id=id;
        this.popularity = popularity;
        this.title = title;
        this.vote_average = vote_average;
        this.overview = overview;
        this.release_date = release_date;
        this.poster_path=poster_path;
    }

    public Movies() {
    }

    public String getPoster_path() {
        return poster_path;
    }

    public float getPopularity() {
        return popularity;
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "Movies{" +
                "popularity=" + popularity +
                ", title='" + title + '\'' +
                ", vote_average=" + vote_average +
                ", overview='" + overview + '\'' +
                ", release_date='" + release_date + '\'' +
                ", id=" + id +
                ", poster_path='" + poster_path + '\'' +
                '}';
    }
}

package com.echangole.mymovies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {

   @SerializedName("title")
   @Expose
   private String title;
   @SerializedName("poster_path")
   @Expose
   private String poster_path;
   @SerializedName("overview")
   @Expose
   private String overview;
   @SerializedName("runtime")
   @Expose
   private String runtime;
   @SerializedName("revenue")
   @Expose
   private String revenue;
   @SerializedName("budget")
   @Expose
   private String budget;
   @SerializedName("genres")
   @Expose
   private List<Genres> genres;
   @SerializedName("vote_average")
   @Expose
   private float vote_average;
   @SerializedName("id")
   @Expose
   private int id;
   @SerializedName("original_name")
   @Expose
   private String original_name;
   @SerializedName("media_type")
   @Expose()
   private String media_type;

   public Movie(String title, String poster_path, String overview, String runtime, String revenue,
                String budget, List<Genres> genres,float vote_average,int id,String media_type,String original_name) {
      this.title = title;
      this.poster_path = poster_path;
      this.overview = overview;
      this.runtime = runtime;
      this.revenue = revenue;
      this.budget = budget;
      this.genres = genres;
      this.vote_average=vote_average;
      this.id=id;
      this.media_type=media_type;
      this.original_name=original_name;
   }

   public Movie() {
   }

   public String getOriginal_name() {
      return original_name;
   }

   public String getMedia_type() {
      return media_type;
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

   public String getPoster_path() {
      return poster_path;
   }

   public String getOverview() {
      return overview;
   }

   public String getRuntime() {
      return runtime;
   }

   public String getRevenue() {
      return revenue;
   }

   public String getBudget() {
      return budget;
   }

   public List<Genres> getGenres() {
      return genres;
   }

   @Override
   public String toString() {
      return "Movie{" +
              "title='" + title + '\'' +
              ", poster_path='" + poster_path + '\'' +
              ", overview='" + overview + '\'' +
              ", runtime='" + runtime + '\'' +
              ", revenue='" + revenue + '\'' +
              ", budget='" + budget + '\'' +
              ", genres=" + genres +
              ", vote_average=" + vote_average +
              ", id=" + id +
              ", original_name='" + original_name + '\'' +
              ", media_type='" + media_type + '\'' +
              '}';
   }
}

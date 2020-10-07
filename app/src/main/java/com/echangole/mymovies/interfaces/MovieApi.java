package com.echangole.mymovies.interfaces;

import com.echangole.mymovies.model.Movie;

import com.echangole.mymovies.response.SearchResponse;
import com.echangole.mymovies.response.TrendResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("/3/search/movie")
    Call<SearchResponse> getMovieList(
            @Query("api_key") String apiKey,
            @Query("query") String param,
            @Query("page") int page);

    @GET("/3/movie/{movie_id}")
    Call<Movie> getMovie(
            @Path("movie_id") String movie_id,
            @Query("api_key") String apiKey
    );
    @GET("/3/trending/all/day")
    Call<TrendResponse> getThread
            (
                    @Query("api_key") String api_key

            );




}

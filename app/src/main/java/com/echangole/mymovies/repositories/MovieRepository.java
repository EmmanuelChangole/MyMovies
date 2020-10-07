package com.echangole.mymovies.repositories;

import androidx.lifecycle.LiveData;

import com.echangole.mymovies.client.MovieClient;
import com.echangole.mymovies.model.Movie;
import com.echangole.mymovies.model.Movies;
import com.echangole.mymovies.response.TrendResponse;

import java.util.List;

public class MovieRepository {
    private static MovieRepository instance;
    private MovieClient movieClient;


    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }

        return instance;
    }

    private MovieRepository() {
        movieClient = MovieClient.getInstance();

    }


    public LiveData<List<Movies>> getmMovies() {
        return movieClient.getMovie();
    }
    public LiveData<Movie> getMovie()
    {
        return movieClient.getmMovies();
    }

    public LiveData<TrendResponse> getTrend()
    {
        return movieClient.getTrendingMovie();
    }
    public void searchMove(String query,int pageNumber)
    {
        if(pageNumber==0)
        {
            pageNumber=1;
        }
        movieClient.searchMovie(query,pageNumber);

    }

    public void getMovie(String id)
    {

        movieClient.getMovie(id);

    }

    public void getTrending()
    {
        movieClient.getTrend();
    }
}

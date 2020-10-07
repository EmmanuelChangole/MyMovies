package com.echangole.mymovies.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.echangole.mymovies.model.Movie;
import com.echangole.mymovies.model.Movies;
import com.echangole.mymovies.repositories.MovieRepository;
import com.echangole.mymovies.response.TrendResponse;

import java.util.List;

public class MovieListViewModel extends ViewModel {
    private MovieRepository movieRepository;

    public MovieListViewModel() {
        this.movieRepository = MovieRepository.getInstance();

    }

    public LiveData<List<Movies>> getMovie() {
        return movieRepository.getmMovies();
    }

    public LiveData<Movie> getmMovie() {
        return movieRepository.getMovie();
    }

    public LiveData<TrendResponse> getTrend()
    {
        return movieRepository.getTrend();
    }


    public void searchMovie(String query, int pageNumber)
    {
        if(pageNumber==0)
        {
            pageNumber=1;
        }
        movieRepository.searchMove(query,pageNumber);

    }
    public void getMovie(String id)
    {
        movieRepository.getMovie(id);

    }

    public void getTrending()
    {
        movieRepository.getTrending();
    }

}

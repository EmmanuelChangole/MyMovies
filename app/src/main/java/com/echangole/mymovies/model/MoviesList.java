package com.echangole.mymovies.model;

import java.util.List;

public class MoviesList
{
    private List<Movies> moviesList;

    public MoviesList(List<Movies> moviesList) {
        this.moviesList = moviesList;
    }

    public List<Movies> getMoviesList() {
        return moviesList;
    }
}

package com.echangole.mymovies.response;


import com.echangole.mymovies.model.MovieList;
import com.echangole.mymovies.model.Movies;
import com.echangole.mymovies.model.MoviesList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchResponse {

    @SerializedName("results")
    @Expose()
    private ArrayList<Movies> Search =new ArrayList<>();

    public void setSearch (ArrayList<Movies> Search) {
        this.Search = Search;
    }

    public ArrayList<Movies> getSearch () {
        return Search;
    }

}

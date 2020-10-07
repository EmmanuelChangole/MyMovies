package com.echangole.mymovies.response;

import com.echangole.mymovies.model.MovieList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TrendResponse
{
    @SerializedName("results")
    @Expose()
    private ArrayList<MovieList> trend =new ArrayList<>();

    public void setTrend(ArrayList<MovieList> trend) {
        this.trend = trend;
    }

    public ArrayList<MovieList> getTrend() {
        return trend;
    }

}

package com.jakester.flicksapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 9/15/2017.
 */

public class ResultsResponse {
    @SerializedName("results")
    List<Movie> movies;

    public ResultsResponse(){
        movies = new ArrayList<Movie>();
    }

    public List<Movie> getMovies(){
        return movies;
    }
}

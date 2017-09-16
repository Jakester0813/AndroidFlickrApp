package com.jakester.flicksapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jake on 9/15/2017.
 */

public class Movie {
    @SerializedName("title")
    String mTitle;

    @SerializedName("overview")
    String mOverview;

    @SerializedName("poster_path")
    String mUrl;

    public String getTitle(){
        return mTitle;
    }

    public String getOverview(){
        return mOverview;
    }

    public String getPosterPath(){
        return "http://image.tmdb.org/t/p/w185/" + mUrl;
    }
}

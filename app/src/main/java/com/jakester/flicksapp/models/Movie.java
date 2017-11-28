package com.jakester.flicksapp.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Jake on 9/15/2017.
 */

@Parcel(analyze={Movie.class})
public class Movie {
    @SerializedName("id")
    int mId;

    @SerializedName("title")
    String mTitle;

    @SerializedName("overview")
    String mOverview;

    @SerializedName("poster_path")
    String mUrl;

    @SerializedName("backdrop_path")
    String mBackdropUrl;

    @SerializedName("vote_average")
    Double mVote;

    @SerializedName("popularity")
    Double mPopularity;

    @SerializedName("release_date")
    String mReleaseDate;

    public int getId(){
        return mId;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getOverview(){
        return mOverview;
    }

    public String getPosterPath(){
        return "http://image.tmdb.org/t/p/w185/" + mUrl;
    }

    public String getBackdropPath(){
        return "http://image.tmdb.org/t/p/w500/" + mBackdropUrl;
    }

    public String getPopularBackdropPath(){
        return "http://image.tmdb.org/t/p/w780/" + mBackdropUrl;
    }

    public Double getVote() { return mVote; }

    public Double getPopularity() { return mPopularity; }

    public String getReleaseDate() { return mReleaseDate; }
}

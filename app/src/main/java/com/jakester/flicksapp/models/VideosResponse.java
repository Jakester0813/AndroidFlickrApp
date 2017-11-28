package com.jakester.flicksapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 9/15/2017.
 */

public class VideosResponse {
    @SerializedName("id")
    int id;

    @SerializedName("results")
    List<Video> videos;

    public VideosResponse(){
        videos = new ArrayList<Video>();
    }

    public List<Video> getVideos(){
        return videos;
    }
}

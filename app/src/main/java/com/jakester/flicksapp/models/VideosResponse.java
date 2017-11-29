package com.jakester.flicksapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 9/15/2017.
 */

public class VideosResponse {

    @SerializedName("results")
    ArrayList<Video> videos;

    public VideosResponse(){
        videos = new ArrayList<Video>();
    }

    public ArrayList<Video> getVideos(){
        return videos;
    }
}

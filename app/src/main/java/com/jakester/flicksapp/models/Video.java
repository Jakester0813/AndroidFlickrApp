package com.jakester.flicksapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jake on 9/15/2017.
 */

public class Video {
    @SerializedName("id")
    String mId;

    @SerializedName("name")
    String mName;

    @SerializedName("key")
    String mKey;



    public String getId(){
        return mId;
    }

    public String getTitle(){
        return mName;
    }

    public String getKey(){
        return mKey;
    }

}

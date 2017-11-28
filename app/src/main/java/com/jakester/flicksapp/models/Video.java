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

    @SerializedName("size")
    int mSize;

    @SerializedName("type")
    String mType;



    public String getId(){
        return mId;
    }

    public String getTitle(){
        return mName;
    }

    public String getKey(){
        return mKey;
    }

    public int getSize(){
        return mSize;
    }

    public String getType() { return mType; }
}

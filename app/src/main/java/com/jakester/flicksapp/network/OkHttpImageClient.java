package com.jakester.flicksapp.network;

/**
 * Created by Jake on 9/17/2017.
 */

public class OkHttpImageClient {

    private static OkHttpImageClient mInstance = null;

    public static OkHttpImageClient getInstance(){
        if(mInstance == null){
            mInstance = new OkHttpImageClient();
        }
        return mInstance;
    }

    
}

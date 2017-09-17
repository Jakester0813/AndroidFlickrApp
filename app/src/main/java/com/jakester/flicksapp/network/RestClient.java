package com.jakester.flicksapp.network;

import com.jakester.flicksapp.util.FlicksConstants;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Jake on 9/17/2017.
 */

public class RestClient {

    private static RestClient mInstance = null;
    OkHttpClient mClient;

    public static RestClient getInstance(){
        if(mInstance == null){
            mInstance = new RestClient();
        }
        return mInstance;
    }

    public OkHttpClient getClient(){
        if(mClient == null){
            mClient = new OkHttpClient();
        }
        return mClient;
    }

    public Request getRequestObject(){

        Request mRequest = new Request.Builder().url(FlicksConstants.MOVIES_URL).build();
        return mRequest;
    }


}

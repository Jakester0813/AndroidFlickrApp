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

    public Request getMoviesRequestObject(){

        Request mRequest = new Request.Builder().url(FlicksConstants.MOVIES_URL).build();
        return mRequest;
    }

    public Request getMovieDetailsRequestObject(int id){
        StringBuilder sb = new StringBuilder(FlicksConstants.MOVIE_BASE_URL)
                .append(id).append(FlicksConstants.MOVIE_API_KEY_QUERY);
        Request mRequest = new Request.Builder().url(sb.toString()).build();
        return mRequest;
    }

    public Request getMovieTrailerRequestObject(int id){
        StringBuilder sb = new StringBuilder(FlicksConstants.MOVIE_BASE_URL)
                .append(id).append(FlicksConstants.MOVIE_VIDEOS_API_KEY_QUERY_URL);
        Request mRequest = new Request.Builder().url(sb.toString()).build();
        return mRequest;
    }


}

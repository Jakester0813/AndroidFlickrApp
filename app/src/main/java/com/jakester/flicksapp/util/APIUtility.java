package com.jakester.flicksapp.util;

import com.jakester.flicksapp.interfaces.FlicksService;
import com.jakester.flicksapp.network.RestClient;

/**
 * Created by Jake on 11/29/2017.
 */

public class APIUtility {

    public static FlicksService getMovieService() {
        return RestClient.getClient().create(FlicksService.class);
    }

}

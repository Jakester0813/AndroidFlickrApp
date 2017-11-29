package com.jakester.flicksapp.interfaces;

import com.jakester.flicksapp.models.Movie;
import com.jakester.flicksapp.models.ResultsResponse;
import com.jakester.flicksapp.models.VideosResponse;
import com.jakester.flicksapp.util.FlicksConstants;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Jake on 11/27/2017.
 */

public interface FlicksService {
    @GET(FlicksConstants.NOW_PLAYING_QUERY)
    Observable<ResultsResponse> getMovies();

    @GET("{id}/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
    Observable<VideosResponse> getVideos(@Path("id") int movieId);
}

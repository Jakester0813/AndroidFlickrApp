package com.jakester.flicksapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakester.flicksapp.R;
import com.jakester.flicksapp.models.Movie;
import com.jakester.flicksapp.network.RestClient;
import com.jakester.flicksapp.util.FlicksConstants;

import org.parceler.Parcels;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MovieDetailActivity extends YouTubeBaseActivity {
    String id;
    Movie movie;
    private TextView mTitle,mReleaseDate,mOverview,mRating;

    YouTubePlayerView mPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        id = getIntent().getStringExtra("id");
        movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        mTitle = (TextView) findViewById(R.id.tv_movie_title);
        mRating = (TextView) findViewById(R.id.tv_movie_rating);
        mReleaseDate = (TextView) findViewById(R.id.tv_movie_release_date);
        mOverview = (TextView) findViewById(R.id.tv_movie_desc);
        mPlayer = (YouTubePlayerView) findViewById(R.id.player);
        mTitle.setText(movie.getTitle());
        StringBuilder sbRelease = new StringBuilder("Release Date: ").append(movie.getReleaseDate());
        mReleaseDate.setText(sbRelease.toString());
        StringBuilder sbRating = new StringBuilder("Rating: ").append(Double.toString(movie.getVote())).append("/10");
        mRating.setText(sbRating.toString());
        mOverview.setText(movie.getOverview());

        mPlayer.initialize(FlicksConstants.APP_KEY,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {
                        // do any work here to cue video, play video, etc.
                        youTubePlayer.cueVideo(id);
                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
    }
}

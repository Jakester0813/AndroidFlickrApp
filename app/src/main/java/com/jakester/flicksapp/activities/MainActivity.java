package com.jakester.flicksapp.activities;


import android.content.Intent;
import android.os.Parcel;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakester.flicksapp.R;
import com.jakester.flicksapp.adapters.MoviesAdapter;
import com.jakester.flicksapp.interfaces.MovieTouchCallback;
import com.jakester.flicksapp.models.Movie;
import com.jakester.flicksapp.models.ResultsResponse;
import com.jakester.flicksapp.models.Video;
import com.jakester.flicksapp.models.VideosResponse;
import com.jakester.flicksapp.network.RestClient;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MovieTouchCallback{

    Toolbar mToolbar;
    RecyclerView mMoviesRecycler;

    MoviesAdapter mMovieAdapter;
    Gson gson;
    RestClient mClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mMoviesRecycler = (RecyclerView) findViewById(R.id.rv_movies);
        mToolbar.setTitle("Now Playing near you");
        setSupportActionBar(mToolbar);

        mMoviesRecycler.setLayoutManager(new LinearLayoutManager(this));


        gson = new GsonBuilder().create();
        mClient = RestClient.getInstance();
        mClient.getClient().newCall(mClient.getMoviesRequestObject()).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 runThread((ArrayList<Movie>) gson.fromJson(response.body().string(), ResultsResponse.class).getMovies());

            }

            @Override
            public void onFailure(Call call, IOException e) {

            }


        });

    }

    private void runThread(final ArrayList<Movie> pList){
        runOnUiThread (new Thread(new Runnable() {
            public void run() {
                mMoviesRecycler.setAdapter(new MoviesAdapter(getApplicationContext(), pList, MainActivity.this));
            }
        }));
    }

    @Override
    public void onClick(final Movie movie, final boolean popular) {
        mClient.getClient().newCall(mClient.getMovieTrailerRequestObject(movie.getId())).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                ArrayList<Video> videos = (ArrayList<Video>) gson.fromJson(response.body().string(), VideosResponse.class).getVideos();
                showMovie(videos.get(0).getKey(), popular, movie);
            }

            @Override
            public void onFailure(Call call, IOException e) {

            }


        });
    }

    private void showMovie(String id, boolean popular, Movie movie){
        if(popular){
            Intent i = new Intent(this, TrailerActivity.class);
            i.putExtra("id", id);
            startActivity(i);
        }
        else{
            Intent i = new Intent(this, MovieDetailActivity.class);
            i.putExtra("id", id);
            i.putExtra("movie", Parcels.wrap(movie));
            startActivity(i);
        }
    }
}

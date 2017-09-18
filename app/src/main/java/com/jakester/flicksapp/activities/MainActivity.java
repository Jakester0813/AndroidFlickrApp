package com.jakester.flicksapp.activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakester.flicksapp.R;
import com.jakester.flicksapp.adapters.MoviesAdapter;
import com.jakester.flicksapp.models.Movie;
import com.jakester.flicksapp.models.ResultsResponse;
import com.jakester.flicksapp.network.RestClient;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ListView mListView;
    MoviesAdapter mMovieAdapter;

    Gson gson;
    RestClient mClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Now Playing near you");
        setSupportActionBar(mToolbar);
        mListView = (ListView) findViewById(R.id.lv_movies);



        gson = new GsonBuilder().create();
        mClient = RestClient.getInstance();
        mClient.getClient().newCall(mClient.getRequestObject()).enqueue(new Callback() {
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
                mMovieAdapter = new MoviesAdapter(MainActivity.this, pList);
                mListView.setAdapter(mMovieAdapter);
            }
        }));
    }

}

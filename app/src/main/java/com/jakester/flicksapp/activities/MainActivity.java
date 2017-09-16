package com.jakester.flicksapp.activities;

import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import cz.msebera.android.httpclient.Header;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakester.flicksapp.R;
import com.jakester.flicksapp.adapters.MoviesAdapter;
import com.jakester.flicksapp.models.Movie;
import com.jakester.flicksapp.models.ResultsResponse;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Gson gson;
    ListView mListView;
    MoviesAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncHttpClient client = new AsyncHttpClient();
        gson = new GsonBuilder().create();
        RequestParams params = new RequestParams();
        client.get("https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed", params, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                ResultsResponse response = gson.fromJson(responseString, ResultsResponse.class);
                mMovieAdapter = new MoviesAdapter(MainActivity.this, (ArrayList<Movie>) response.getMovies());
                mListView = (ListView) findViewById(R.id.lv_movies);
                mListView.setAdapter(mMovieAdapter);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            }
        });
    }
}

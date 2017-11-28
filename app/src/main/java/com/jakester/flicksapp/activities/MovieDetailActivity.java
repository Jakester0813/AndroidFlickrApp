package com.jakester.flicksapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakester.flicksapp.R;
import com.jakester.flicksapp.network.RestClient;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MovieDetailActivity extends AppCompatActivity {
    RestClient mClient;
    int id;
    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        id = getIntent().getIntExtra("id",0);

        gson = new GsonBuilder().create();
        mClient = RestClient.getInstance();
        mClient.getClient().newCall(mClient.getMovieDetailsRequestObject(id)).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //runThread((ArrayList<Movie>) gson.fromJson(response.body().string(), ResultsResponse.class).getMovies());

            }

            @Override
            public void onFailure(Call call, IOException e) {

            }


        });
    }
}

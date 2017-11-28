package com.jakester.flicksapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jakester.flicksapp.R;
import com.jakester.flicksapp.network.RestClient;

public class MovieDetailActivity extends AppCompatActivity {
    RestClient mClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
    }
}

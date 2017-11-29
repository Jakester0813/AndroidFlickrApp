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
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakester.flicksapp.R;
import com.jakester.flicksapp.adapters.MoviesAdapter;
import com.jakester.flicksapp.interfaces.FlicksService;
import com.jakester.flicksapp.interfaces.MovieTouchCallback;
import com.jakester.flicksapp.models.Movie;
import com.jakester.flicksapp.models.ResultsResponse;
import com.jakester.flicksapp.models.Video;
import com.jakester.flicksapp.models.VideosResponse;
import com.jakester.flicksapp.network.RestClient;
import com.jakester.flicksapp.util.APIUtility;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MovieTouchCallback{

    Toolbar mToolbar;
    RecyclerView mMoviesRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mMoviesRecycler = (RecyclerView) findViewById(R.id.rv_movies);
        mToolbar.setTitle("Now Playing near you");
        setSupportActionBar(mToolbar);

        mMoviesRecycler.setLayoutManager(new LinearLayoutManager(this));

        getPlayingMovies();

    }

    private void getPlayingMovies(){
        FlicksService service = APIUtility.getMovieService();
        service.getMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultsResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResultsResponse resultsResponse) {
                        mMoviesRecycler.setAdapter(new MoviesAdapter(getApplicationContext(),
                                resultsResponse.getMovies(), MainActivity.this));
                    }
                });

    }

    private void getVideos(final Movie movie){
        FlicksService service = APIUtility.getMovieService();
        service.getVideos(movie.getId()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideosResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(VideosResponse videosResponse) {
                        showMovie(videosResponse.getVideos().get(0).getKey(), movie);
                    }
                });

    }


    @Override
    public void onClick(final Movie movie) {
        getVideos(movie);
    }

    private void showMovie(String id, Movie movie){
        if(movie.getPopularity() >= 650.0){
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

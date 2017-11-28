package com.jakester.flicksapp.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.jakester.flicksapp.R;
import com.jakester.flicksapp.models.Movie;

import java.util.Calendar;

/**
 * Created by Jake on 11/27/2017.
 */

public class MovieDetailDialog extends DialogFragment {
    private TextView mTitle,mReleaseDate,mOverview,mRating;
    String title, releaseDate, overView;
    Double rating;

    public MovieDetailDialog(){

    }

    public static MovieDetailDialog newInstance(Movie movie){
        MovieDetailDialog frag = new MovieDetailDialog();
        Bundle args = new Bundle();
        args.putString("title",movie.getTitle());
        args.putString("release_date",movie.getReleaseDate());
        args.putString("overview",movie.getOverview());
        args.putDouble("rating",movie.getVote());
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("title");
        releaseDate = getArguments().getString("release_date");
        overView = getArguments().getString("overview");
        rating = getArguments().getDouble("rating");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_movie_details, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTitle = (TextView) view.findViewById(R.id.tv_movie_title);
        mRating = (TextView) view.findViewById(R.id.tv_movie_rating);
        mReleaseDate = (TextView) view.findViewById(R.id.tv_movie_release_date);
        mOverview = (TextView) view.findViewById(R.id.tv_movie_desc);

        mTitle.setText(title);
        StringBuilder sbRelease = new StringBuilder("Release Date: ").append(releaseDate);
        mReleaseDate.setText(sbRelease.toString());
        StringBuilder sbRating = new StringBuilder("Rating: ").append(Double.toString(rating)).append("/10");
        mRating.setText(sbRating.toString());
        mOverview.setText(overView);

    }
}

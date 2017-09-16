package com.jakester.flicksapp.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakester.flicksapp.R;
import com.jakester.flicksapp.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 9/15/2017.
 */

public class MoviesAdapter extends ArrayAdapter<Movie> {

    ArrayList<Movie> mMovies;

    public MoviesAdapter(@NonNull Context context, ArrayList<Movie> pMovies) {
        super(context, 0, pMovies);
        mMovies = pMovies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // Lookup view for data population
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_row, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_movie_poster);
        Picasso.with(parent.getContext()).load(mMovies.get(position).getPosterPath()).fit()
                .centerCrop().into(imageView);
        TextView movieTitle = (TextView) convertView.findViewById(R.id.tv_movie_title);
        movieTitle.setText(mMovies.get(position).getTitle());
        TextView movieDescription = (TextView) convertView.findViewById(R.id.tv_movie_desc);
        movieDescription.setText(mMovies.get(position).getOverview());
        return convertView;

    }
}

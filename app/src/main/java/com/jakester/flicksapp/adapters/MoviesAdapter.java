package com.jakester.flicksapp.adapters;

import android.content.Context;
import android.content.res.Configuration;
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
    boolean mPortrait;

    private static class MovieViewHolder {
        TextView mTitle;
        TextView mDescription;
        ImageView mImage;
    }

    public MoviesAdapter(@NonNull Context context, ArrayList<Movie> pMovies) {
        super(context, 0, pMovies);
        mMovies = pMovies;
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            mPortrait = true;
        }
        else{
            mPortrait = false;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // Lookup view for data population
        Movie movie = getItem(position);
        MovieViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new MovieViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_row, parent, false);
            viewHolder.mTitle = (TextView) convertView.findViewById(R.id.tv_movie_title);
            viewHolder.mDescription = (TextView) convertView.findViewById(R.id.tv_movie_desc);
            viewHolder.mImage = (ImageView) convertView.findViewById(R.id.iv_movie_poster);
            //Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (MovieViewHolder) convertView.getTag();
        }
        if(mPortrait) {
            Picasso.with(parent.getContext()).load(movie.getPosterPath()).fit()
                    .centerCrop().placeholder(R.drawable.placeholder_movie_image).into(viewHolder.mImage);
        }
        else{
            Picasso.with(parent.getContext()).load(movie.getBackdropPath()).fit()
                    .centerCrop().placeholder(R.drawable.placeholder_movie_image_land).into(viewHolder.mImage);
        }

        viewHolder.mTitle.setText(mMovies.get(position).getTitle());
        viewHolder.mDescription.setText(mMovies.get(position).getOverview());
        return convertView;

    }
}
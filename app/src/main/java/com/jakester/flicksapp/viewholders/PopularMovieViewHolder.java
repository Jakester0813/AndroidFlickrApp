package com.jakester.flicksapp.viewholders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jakester.flicksapp.R;
import com.jakester.flicksapp.activities.MovieDetailActivity;
import com.jakester.flicksapp.activities.TrailerActivity;
import com.jakester.flicksapp.interfaces.MovieTouchCallback;
import com.jakester.flicksapp.models.Movie;

/**
 * Created by Jake on 11/27/2017.
 */

public class PopularMovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView mMoviePoster;
    Context mContext;
    Movie mMovie;
    MovieTouchCallback mCallback;

    public PopularMovieViewHolder(View itemView, Context context, MovieTouchCallback callback) {
        super(itemView);
        this.mMoviePoster = (ImageView) itemView.findViewById(R.id.iv_movie_poster);
        this.mContext = context;
        this.mCallback = callback;
        itemView.setOnClickListener(this);
    }

    public void bind(Movie movie, boolean portrait){
        mMovie = movie;
        if(portrait){
            Glide.with(mContext).load(mMovie.getBackdropPath()).fitCenter()
                    .placeholder(R.drawable.placeholder_movie_image).into(mMoviePoster);
        }
        else{
            Glide.with(mContext).load(mMovie.getPopularBackdropPath()).fitCenter()
                    .placeholder(R.drawable.placeholder_movie_image).into(mMoviePoster);
        }

    }

    @Override
    public void onClick(View view) {
        //Intent i = new Intent(mContext, TrailerActivity.class);
        //i.putExtra("id", mMovie.getId());
        //mContext.startActivity(i);
        mCallback.onClick(mMovie, true);
    }
}

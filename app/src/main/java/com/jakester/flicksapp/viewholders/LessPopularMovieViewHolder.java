package com.jakester.flicksapp.viewholders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakester.flicksapp.R;
import com.jakester.flicksapp.activities.MovieDetailActivity;
import com.jakester.flicksapp.interfaces.MovieTouchCallback;
import com.jakester.flicksapp.models.Movie;

/**
 * Created by Jake on 11/27/2017.
 */

public class LessPopularMovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView mMoviePoster;
    TextView mTitle, mOverview;
    Context mContext;
    Movie mMovie;
    MovieTouchCallback mCallback;

    public LessPopularMovieViewHolder(View itemView, Context context, MovieTouchCallback callback) {
        super(itemView);
        this.mMoviePoster = (ImageView) itemView.findViewById(R.id.iv_movie_poster);
        this.mTitle = (TextView) itemView.findViewById(R.id.tv_movie_title);
        this.mOverview = (TextView) itemView.findViewById(R.id.tv_movie_desc);
        this.mContext = context;
        this.mCallback = callback;
        itemView.setOnClickListener(this);
    }

    public void bind(Movie movie, boolean portrait){
        mMovie = movie;
        if(portrait){
            Glide.with(mContext).load(movie.getPosterPath()).centerCrop()
                    .placeholder(R.drawable.placeholder_movie_image).into(mMoviePoster);
        }
        else{
            Glide.with(mContext).load(movie.getBackdropPath()).centerCrop()
                    .placeholder(R.drawable.placeholder_movie_image).into(mMoviePoster);
        }
        mTitle.setText(mMovie.getTitle());
        mOverview.setText(mMovie.getOverview());

    }

    @Override
    public void onClick(View view) {
        //Intent i = new Intent(mContext, MovieDetailActivity.class);
        //i.putExtra("id", mMovie.getId());
        //mContext.startActivity(i);
        mCallback.onClick(mMovie, false);
    }
}

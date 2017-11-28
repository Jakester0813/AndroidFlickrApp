package com.jakester.flicksapp.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jakester.flicksapp.R;
import com.jakester.flicksapp.models.Movie;

/**
 * Created by Jake on 11/27/2017.
 */

public class PopularMovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView mMoviePoster;
    Context mContext;

    public PopularMovieViewHolder(View itemView, Context context) {
        super(itemView);
        this.mMoviePoster = (ImageView) itemView.findViewById(R.id.iv_movie_poster);
        this.mContext = context;
    }

    public void bind(Movie movie, boolean portrait){
        if(portrait){
            Glide.with(mContext).load(movie.getBackdropPath()).fitCenter()
                    .placeholder(R.drawable.placeholder_movie_image).into(mMoviePoster);
        }
        else{
            Glide.with(mContext).load(movie.getPopularBackdropPath()).fitCenter()
                    .placeholder(R.drawable.placeholder_movie_image).into(mMoviePoster);
        }

    }

    @Override
    public void onClick(View view) {

    }
}

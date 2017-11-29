package com.jakester.flicksapp.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakester.flicksapp.R;
import com.jakester.flicksapp.interfaces.MovieTouchCallback;
import com.jakester.flicksapp.models.Movie;
import com.jakester.flicksapp.viewholders.LessPopularMovieViewHolder;
import com.jakester.flicksapp.viewholders.PopularMovieViewHolder;

import java.util.ArrayList;

/**
 * Created by Jake on 9/15/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Movie> mMovies;
    boolean mPortrait;
    private final int MOVIE = 0, POPULAR_MOVIE = 1;
    Context mContext;
    MovieTouchCallback mCallback;


    public MoviesAdapter(@NonNull Context context, ArrayList<Movie> pMovies, MovieTouchCallback callback) {
        mMovies = pMovies;
        mContext = context;
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            mPortrait = true;
        }
        else{
            mPortrait = false;
        }
        mCallback = callback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType) {
            case MOVIE:
                View v1 = inflater.inflate(R.layout.movie_row, viewGroup, false);
                viewHolder = new LessPopularMovieViewHolder(v1, mContext, mCallback);
                break;
            case POPULAR_MOVIE:
                View v2 = inflater.inflate(R.layout.popular_movie_row, viewGroup, false);
                viewHolder = new PopularMovieViewHolder(v2, mContext, mCallback);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        switch (holder.getItemViewType()) {
            case MOVIE:
                LessPopularMovieViewHolder viewHolder = (LessPopularMovieViewHolder) holder;
                viewHolder.bind(movie, mPortrait);
                break;
            case POPULAR_MOVIE:
                PopularMovieViewHolder popViewHolder = (PopularMovieViewHolder) holder;
                popViewHolder.bind(movie, mPortrait);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    @Override
    public int getItemViewType(int position) {
        mMovies.get(position).getPopularity();
        if (mMovies.get(position).getPopularity() >= 650.0) {
            return POPULAR_MOVIE;
        } else {
            return MOVIE;
        }
    }
}

package com.jakester.flicksapp.interfaces;

import android.view.View;

import com.jakester.flicksapp.models.Movie;

/**
 * Created by Jake on 11/27/2017.
 */

public interface MovieTouchCallback {
    void onClick(Movie movie, boolean popular);
}

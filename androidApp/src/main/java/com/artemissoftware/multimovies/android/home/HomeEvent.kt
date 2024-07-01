package com.artemissoftware.multimovies.android.home

sealed class HomeEvent {
    data object LoadNextMovies : HomeEvent()
}
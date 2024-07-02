package com.artemissoftware.multimovies.android

import com.artemissoftware.multimovies.android.detail.DetailState
import com.artemissoftware.multimovies.android.home.HomeState
import com.artemissoftware.multimovies.domain.models.Movie

internal object PreviewData {

    val movie = Movie(
        id = 1,
        title = "Movie 1",
        description = "The first movie",
        imageUrl = "Https://www.movie1.jpg",
        releaseDate = "01-01-1990"
    )

    val homeState = HomeState(
        movies = listOf(movie)
    )

    val detailState = DetailState(
        movie = movie
    )
}
package com.artemissoftware.multimovies.domain.repository

import com.artemissoftware.multimovies.domain.models.Movie

internal interface MovieRepository {
    suspend fun getMovies(page: Int): List<Movie>

    suspend fun getMovie(movieId: Int): Movie
}
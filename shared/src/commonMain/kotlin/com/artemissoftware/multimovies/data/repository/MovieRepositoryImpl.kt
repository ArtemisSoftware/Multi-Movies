package com.artemissoftware.multimovies.data.repository

import com.artemissoftware.multimovies.data.mappers.toMovie
import com.artemissoftware.multimovies.data.network.source.MovieSource
import com.artemissoftware.multimovies.domain.models.Movie
import com.artemissoftware.multimovies.domain.repository.MovieRepository

internal class MovieRepositoryImpl(
    private val movieSource: MovieSource
): MovieRepository {

    override suspend fun getMovies(page: Int): List<Movie> {
        return movieSource.getMovies(page = page).results.map {
            it.toMovie()
        }
    }

    override suspend fun getMovie(movieId: Int): Movie {
        return movieSource.getMovie(movieId = movieId).toMovie()
    }
}
package com.artemissoftware.multimovies.data.repository

import com.artemissoftware.multimovies.data.mappers.toMovie
import com.artemissoftware.multimovies.data.network.HandleApi
import com.artemissoftware.multimovies.data.network.source.MovieSource
import com.artemissoftware.multimovies.domain.Resource
import com.artemissoftware.multimovies.domain.models.Movie
import com.artemissoftware.multimovies.domain.repository.MovieRepository

internal class MovieRepositoryImpl(
    private val movieSource: MovieSource
): MovieRepository {

    override suspend fun getMovies(page: Int): Resource<List<Movie>> {
        return HandleApi.safeApiCall {
            movieSource.getMovies(page = page).results.map {
                it.toMovie()
            }
        }
    }

    override suspend fun getMovie(movieId: Int): Resource<Movie> {
        return HandleApi.safeApiCall { movieSource.getMovie(movieId = movieId).toMovie() }
    }
}
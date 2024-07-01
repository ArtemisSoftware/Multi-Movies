package com.artemissoftware.multimovies.data.network.source

import com.artemissoftware.multimovies.data.network.service.MovieService
import com.artemissoftware.multimovies.util.dispatcher.Dispatcher
import kotlinx.coroutines.withContext

internal class MovieSource(
    private val apiService: MovieService,
    private val dispatcher: Dispatcher
) {

    suspend fun getMovies(page: Int) = withContext(dispatcher.io){
        apiService.getMovies(page = page)
    }

    suspend fun getMovie(movieId: Int) = withContext(dispatcher.io){
        apiService.getMovie(movieId = movieId)
    }
}
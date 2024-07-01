package com.artemissoftware.multimovies.data.network.service

import com.artemissoftware.multimovies.data.network.MovieApi
import com.artemissoftware.multimovies.data.network.dto.MovieDto
import com.artemissoftware.multimovies.data.network.dto.MoviesDto
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class MovieService: MovieApi() {

    suspend fun getMovies(page: Int = 1): MoviesDto = client.get {
        pathUrl("movie/popular")
        parameter("page", page)
    }.body()

    suspend fun getMovie(movieId: Int): MovieDto = client.get {
        pathUrl("movie/${movieId}")
    }.body()
}
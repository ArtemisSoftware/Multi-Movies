package com.artemissoftware.multimovies.domain.usecase

import com.artemissoftware.multimovies.domain.models.Movie
import com.artemissoftware.multimovies.domain.repository.MovieRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMoviesUseCase: KoinComponent {
    private val repository: MovieRepository by inject()

    suspend operator fun invoke(page: Int) = repository.getMovies(page = page)
}
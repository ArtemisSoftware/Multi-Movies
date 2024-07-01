package com.artemissoftware.multimovies.domain.di

import com.artemissoftware.multimovies.domain.usecase.GetMovieUseCase
import com.artemissoftware.multimovies.domain.usecase.GetMoviesUseCase
import org.koin.dsl.module

private val useCaseModule = module {
    factory { GetMoviesUseCase() }
    factory { GetMovieUseCase() }
}
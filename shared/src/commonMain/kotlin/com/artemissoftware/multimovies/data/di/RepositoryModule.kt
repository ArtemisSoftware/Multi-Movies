package com.artemissoftware.multimovies.data.di

import com.artemissoftware.multimovies.data.repository.MovieRepositoryImpl
import com.artemissoftware.multimovies.domain.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}
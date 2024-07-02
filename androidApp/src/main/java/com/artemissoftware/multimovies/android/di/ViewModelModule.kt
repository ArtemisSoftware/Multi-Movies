package com.artemissoftware.multimovies.android.di

import com.artemissoftware.multimovies.android.detail.DetailViewModel
import com.artemissoftware.multimovies.android.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(getMoviesUseCase = get()) }
    viewModel { DetailViewModel(getMovieUseCase = get(), savedStateHandle = get()) }
}
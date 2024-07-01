package com.artemissoftware.multimovies.android.di

import com.artemissoftware.multimovies.android.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
//    viewModel { params -> DetailViewModel(getMovieUseCase = get(), movieId = params.get()) }
}
package com.artemissoftware.multimovies.data.network.di

import com.artemissoftware.multimovies.data.network.service.MovieService
import com.artemissoftware.multimovies.data.network.source.MovieSource
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.dsl.module

val networkModule = module {
    factory { MovieSource(get(), get()) }
    factory { MovieService() }
}.also {
    Napier.base(DebugAntilog())
}
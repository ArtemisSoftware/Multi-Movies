package com.artemissoftware.multimovies.util.di

import com.artemissoftware.multimovies.util.dispatcher.provideDispatcher
import org.koin.dsl.module

val utilityModule = module {
    factory { provideDispatcher() }
}
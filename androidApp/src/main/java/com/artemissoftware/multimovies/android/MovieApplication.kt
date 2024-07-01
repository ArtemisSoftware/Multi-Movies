package com.artemissoftware.multimovies.android

import android.app.Application
import com.artemissoftware.multimovies.android.di.viewModelModule
import com.artemissoftware.multimovies.data.di.repositoryModule
import com.artemissoftware.multimovies.data.network.di.networkModule
import com.artemissoftware.multimovies.domain.di.useCaseModule
import com.artemissoftware.multimovies.util.di.utilityModule
import org.koin.core.context.GlobalContext.startKoin

class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(networkModule)
            modules(utilityModule)
            modules(repositoryModule)
            modules(useCaseModule)
            modules(viewModelModule)
        }
    }
}
package com.github.kiolk.githubwatch

import android.app.Application
import com.github.kiolk.githubwatch.di.networkModule
import com.github.kiolk.githubwatch.di.repositoryModule
import com.github.kiolk.githubwatch.di.viewModelModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(viewModelModule, networkModule, repositoryModule)
        }
    }
}

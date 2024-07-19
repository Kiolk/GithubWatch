package com.github.kiolk.githubwatch

import android.app.Application
import com.github.kiolk.githubwatch.di.appModule
import com.github.kiolk.githubwatch.di.networkModule
import com.github.kiolk.githubwatch.di.repositoryModule
import com.github.kiolk.githubwatch.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(viewModelModule, networkModule, repositoryModule, appModule)
        }
    }
}

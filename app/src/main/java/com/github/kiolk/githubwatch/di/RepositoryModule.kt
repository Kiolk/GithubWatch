package com.github.kiolk.githubwatch.di

import com.github.kiolk.githubwatch.data.settings.AuthorisationRepository
import com.github.kiolk.githubwatch.data.settings.AuthorisationRepositoryImpl
import com.github.kiolk.githubwatch.data.settings.SettingsRepository
import com.github.kiolk.githubwatch.data.settings.SettingsRepositoryImpl
import com.github.kiolk.githubwatch.data.settings.datasource.AuthorisationDataSource
import com.github.kiolk.githubwatch.data.settings.datasource.AuthorisationDataSourceImpl
import com.github.kiolk.githubwatch.data.settings.datasource.LocalSettingsDataSource
import com.github.kiolk.githubwatch.data.settings.datasource.SettingsDataSource
import com.github.kiolk.githubwatch.data.statistics.StatisticsRepository
import com.github.kiolk.githubwatch.data.statistics.StatisticsRepositoryImpl
import com.github.kiolk.githubwatch.data.statistics.datasource.RemoteStatisticsDataSource
import com.github.kiolk.githubwatch.data.statistics.datasource.StatisticsDataSource
import org.koin.dsl.module

val repositoryModule = module {

    single<StatisticsRepository> { StatisticsRepositoryImpl(get()) }
    single<StatisticsDataSource> { RemoteStatisticsDataSource(get()) }

    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
    single<SettingsDataSource> { LocalSettingsDataSource(get()) }
    single<AuthorisationDataSource> { AuthorisationDataSourceImpl(get()) }
    single<AuthorisationRepository> { AuthorisationRepositoryImpl(get(), get()) }
}

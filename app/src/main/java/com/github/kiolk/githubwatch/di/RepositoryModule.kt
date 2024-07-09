package com.github.kiolk.githubwatch.di

import com.github.kiolk.githubwatch.data.statistics.StatisticsRepository
import com.github.kiolk.githubwatch.data.statistics.StatisticsRepositoryImpl
import com.github.kiolk.githubwatch.data.statistics.datasource.RemoteStatisticsDataSource
import com.github.kiolk.githubwatch.data.statistics.datasource.StatisticsDataSource
import org.koin.dsl.module

val repositoryModule = module {

    single<StatisticsRepository> { StatisticsRepositoryImpl(get()) }
    single<StatisticsDataSource> { RemoteStatisticsDataSource(get()) }
}

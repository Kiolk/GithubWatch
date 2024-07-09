package com.github.kiolk.githubwatch.data.statistics

import com.github.kiolk.githubwatch.data.statistics.datasource.StatisticsDataSource
import com.github.kiolk.githubwatch.data.statistics.models.ChartDataModel
import com.github.kiolk.githubwatch.domain.models.ChartData

class StatisticsRepositoryImpl(private val statisticsDataSource: StatisticsDataSource) :
    StatisticsRepository {
    override suspend fun getUserStatistics(): ChartData {
        return statisticsDataSource.getUserStatistics().mapToData()
    }
}

private fun ChartDataModel.mapToData(): ChartData {
    return ChartData(this.data)
}

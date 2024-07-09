package com.github.kiolk.githubwatch.data.statistics.datasource

import com.github.kiolk.githubwatch.data.statistics.models.ChartDataModel

class RemoteStatisticsDataSource(private val api: StatisticsApi) : StatisticsDataSource {
    override suspend fun getUserStatistics(): ChartDataModel {
        val response = api.getUserStatistics()

        return ChartDataModel(response.joinToString { it.toString() })
    }
}

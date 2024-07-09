package com.github.kiolk.githubwatch.data.statistics.datasource

import com.github.kiolk.githubwatch.data.statistics.models.ChartDataModel

interface StatisticsDataSource {

    suspend fun getUserStatistics(): ChartDataModel
}

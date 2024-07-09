package com.github.kiolk.githubwatch.data.statistics

import com.github.kiolk.githubwatch.domain.models.ChartData

interface StatisticsRepository {

    suspend fun getUserStatistics(): ChartData
}

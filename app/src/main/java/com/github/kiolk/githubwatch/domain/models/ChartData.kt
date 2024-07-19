package com.github.kiolk.githubwatch.domain.models

data class ChartData(
    val weeks: List<WeekStatistic> = emptyList(),
)

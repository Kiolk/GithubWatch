package com.github.kiolk.githubwatch.domain.models

data class MonthStatistic(
    val month: String,
    val year: Int = 2024,
    val contributions: Int,
)

package com.github.kiolk.githubwatch.presentation.screens.chart.view

import com.github.kiolk.githubwatch.domain.models.MonthStatistic
import com.github.kiolk.githubwatch.domain.models.WeekStatistic

sealed class RowItemModel {
    data class WeekItemModel(val week: WeekStatistic) : RowItemModel()
    data class MonthHeaderItemModel(val monthStatistic: MonthStatistic) : RowItemModel()
}

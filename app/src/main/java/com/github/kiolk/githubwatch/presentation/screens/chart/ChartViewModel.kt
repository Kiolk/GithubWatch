package com.github.kiolk.githubwatch.presentation.screens.chart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kiolk.githubwatch.data.statistics.StatisticsRepository
import com.github.kiolk.githubwatch.domain.models.ChartData
import com.github.kiolk.githubwatch.domain.models.DayStatistic
import com.github.kiolk.githubwatch.domain.models.MonthStatistic
import com.github.kiolk.githubwatch.presentation.screens.chart.view.ChartModel
import com.github.kiolk.githubwatch.presentation.screens.chart.view.RowItemModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import java.util.Locale

class ChartViewModel(private val statisticsRepository: StatisticsRepository) : ViewModel() {

    private val _chartData = MutableStateFlow(ChartModel(weeks = emptyList()))
    val chartData = _chartData.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        fetchChartData()
    }

    private fun fetchChartData() {
        viewModelScope.launch {
            val chartData = statisticsRepository.getUserStatistics("Kiolk")
            _chartData.emit(chartData.toChartModel())
            _isLoading.value = false
        }
    }

    fun onRefreshPress() {
        _isLoading.value = true
        fetchChartData()
    }
}

private fun ChartData.toChartModel(): ChartModel {
    val list: List<DayStatistic> = this.weeks.flatMap { it.contributionDays }
    val groupByMonth = list.groupBy { "${it.date.year}-${it.date.monthNumber}" }
    val rowItemModels = mutableListOf<RowItemModel>()

    this.weeks.forEachIndexed { index, it ->
        val firstDay = it.firstDay
        if (index == this.weeks.size - 1) {
            rowItemModels.add(RowItemModel.WeekItemModel(it))
            rowItemModels.add(getMonthStatistics(firstDay, groupByMonth, isCurrentMonth = true))
        } else {
            if (firstDay.dayOfMonth == 1) {
                rowItemModels.add(getMonthStatistics(firstDay, groupByMonth))
                rowItemModels.add(RowItemModel.WeekItemModel(it))
            } else {
                rowItemModels.add(RowItemModel.WeekItemModel(it))
            }
        }
    }

    return ChartModel(weeks = rowItemModels)
}

fun getMonthStatistics(
    firstDay: LocalDate,
    monthStats: Map<String, List<DayStatistic>>,
    isCurrentMonth: Boolean = false
): RowItemModel.MonthHeaderItemModel {
    var month = firstDay.monthNumber
    var year = firstDay.year

    if (!isCurrentMonth) {
        month = if (firstDay.monthNumber > 1) firstDay.monthNumber - 1 else 12
        year = if (firstDay.monthNumber > 1) firstDay.year else firstDay.year - 1
    }

    return RowItemModel.MonthHeaderItemModel(
        MonthStatistic(
            month = LocalDate(year, month, 1).month.name.substring(0, 1)
                .uppercase(Locale.getDefault()) + LocalDate(
                year,
                month,
                1
            ).month.name.substring(1).lowercase(Locale.getDefault()),
            year = year,
            contributions = monthStats["${year}-${month}"]?.sumOf { it.contributionCount }
                ?: 0
        )
    )
}

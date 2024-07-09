package com.github.kiolk.githubwatch.presentation.screens.chart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kiolk.githubwatch.data.statistics.StatisticsRepository
import com.github.kiolk.githubwatch.domain.models.ChartData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChartViewModel(private val statisticsRepository: StatisticsRepository) : ViewModel() {

    private val _chartData = MutableStateFlow(ChartData("1"))
    val chartData = _chartData.asStateFlow()

    init {
        fetchChartData()
    }

    private fun fetchChartData() {
        viewModelScope.launch {
            val chartData = statisticsRepository.getUserStatistics()
            _chartData.emit(chartData)
        }
    }
}

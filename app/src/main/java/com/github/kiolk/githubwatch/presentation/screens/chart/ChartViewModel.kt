package com.github.kiolk.githubwatch.presentation.screens.chart

import androidx.lifecycle.ViewModel
import com.github.kiolk.githubwatch.domain.models.ChartData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChartViewModel : ViewModel() {

    private val _chartData = MutableStateFlow(ChartData("1"))
    val chartData = _chartData.asStateFlow()
}

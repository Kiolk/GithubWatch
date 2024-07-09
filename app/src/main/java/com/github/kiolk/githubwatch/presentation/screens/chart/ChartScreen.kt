package com.github.kiolk.githubwatch.presentation.screens.chart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices.SMALL_ROUND
import com.github.kiolk.githubwatch.presentation.theme.GitHubWatchTheme
import com.github.kiolk.githubwatch.presentation.theme.WeekRow
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChartScreen() {
    val chartViewModel: ChartViewModel = koinViewModel()
    val chartData by chartViewModel.chartData.collectAsState()

    Text(chartData.dates, color = Color.White)
    ScalingLazyColumn {
        val item = (0..100).map {
            it
        }

        items(item.size) {
            WeekRow()
        }
    }
}

@Preview(device = SMALL_ROUND, showSystemUi = true)
@Composable
fun ChartPreview() {
    GitHubWatchTheme {
        Box(modifier = Modifier.fillMaxSize())
    }
}

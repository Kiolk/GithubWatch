package com.github.kiolk.githubwatch.presentation.screens.chart

import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.foundation.ExperimentalWearFoundationApi
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.foundation.rememberActiveFocusRequester
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.SwipeToDismissValue
import androidx.wear.tooling.preview.devices.WearDevices.SMALL_ROUND
import com.github.kiolk.githubwatch.presentation.theme.GitHubWatchTheme
import com.github.kiolk.githubwatch.presentation.screens.chart.view.WeekRow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalWearFoundationApi::class)
@Composable
fun ChartScreen() {
    val chartViewModel: ChartViewModel = koinViewModel()
    val chartData by chartViewModel.chartData.collectAsState()
    val isLoading by chartViewModel.isLoading.collectAsState()

    val state = rememberScalingLazyListState()
    val focusRequester = rememberActiveFocusRequester()
    val coroutineScope = rememberCoroutineScope()
    val size = chartData.weeks.size

    Scaffold(positionIndicator = { PositionIndicator(scalingLazyListState = state) }) {
        Box(modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = {
                    chartViewModel.onRefreshPress()
                }, onTap = {
                    chartViewModel.onRefreshPress()
                })
            }) {
            ScalingLazyColumn(modifier = Modifier
                .onRotaryScrollEvent {
                    coroutineScope.launch {
                        state.scrollBy(it.verticalScrollPixels)
                        state.animateScrollBy(0f)
                    }
                    true
                }
                .focusRequester(focusRequester)
                .focusable()
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onLongPress = {
                        chartViewModel.onRefreshPress()
                    })
                },
                state = state
            ) {
                if (isLoading) {
                    item {
                        CircularProgressIndicator()
                    }
                } else {
                    items(size) { index ->
                        WeekRow(chartData.weeks[size - 1 - index])
                    }
                }
            }
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

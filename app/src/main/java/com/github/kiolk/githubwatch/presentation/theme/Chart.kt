package com.github.kiolk.githubwatch.presentation.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.tooling.preview.devices.WearDevices.SMALL_ROUND

@Composable
fun Chart() {
    ScalingLazyColumn() {
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
        Box(modifier = Modifier.fillMaxSize())}}

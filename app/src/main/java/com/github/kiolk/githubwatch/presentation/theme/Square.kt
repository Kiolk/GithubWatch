package com.github.kiolk.githubwatch.presentation.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.tooling.preview.devices.WearDevices.SMALL_ROUND

@Composable
fun Square(color: Color = Color.White, modifier: Modifier = Modifier) {
    Box(
        modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(2.dp))
            .background(color)
    )
}

@Preview(device = SMALL_ROUND, showSystemUi = true)
@Composable
fun PreviewSquare() {
    GitHubWatchTheme {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Square(
                Color.Blue
            )
        }
    }
}

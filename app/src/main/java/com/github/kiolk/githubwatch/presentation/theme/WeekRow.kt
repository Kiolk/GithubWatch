package com.github.kiolk.githubwatch.presentation.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.tooling.preview.devices.WearDevices.SMALL_ROUND

@Composable
fun WeekRow() {
    BoxWithConstraints(
        propagateMinConstraints = true,
        content = {
            val boxWithConstraintsScope = this
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
//            Text(text = "Jun")
//            Box(modifier = Modifier.width(2.dp))
                Square(
                    modifier = Modifier
                        .weight(1f)
                )
                Box(modifier = Modifier.width(2.dp))
                Square(
                    modifier = Modifier
                        .weight(1f)
                )
                Box(modifier = Modifier.width(2.dp))
                Square(
                    modifier = Modifier
                        .weight(1f)
                )
                Box(modifier = Modifier.width(2.dp))
                Square(
                    modifier = Modifier
                        .weight(1f)
                )
                Box(modifier = Modifier.width(2.dp))
                Square(
                    modifier = Modifier
                        .weight(1f)
                )
                Box(modifier = Modifier.width(2.dp))
                Square(
                    modifier = Modifier
                        .weight(1f)
                )
                Box(modifier = Modifier.width(2.dp))
                Square(
                    modifier = Modifier
                        .weight(1f)
                )
                Box(modifier = Modifier.width(2.dp))
//            Text(text = "Jun")
            }
        })
}

@Preview(device = SMALL_ROUND, showSystemUi = true)
@Composable
fun WeekRowPreview() {
    GitHubWatchTheme {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            WeekRow()
        }
    }
}

package com.github.kiolk.githubwatch.presentation.screens.chart.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen() {
    val settingsViewModel: SettingsViewModel = koinViewModel()
    val userName = settingsViewModel.userName.collectAsState()
    val focusRequester = remember { FocusRequester() }

    val state = rememberScalingLazyListState()

    Scaffold(positionIndicator = { PositionIndicator(scalingLazyListState = state) }) {
        Box {
            ScalingLazyColumn(userScrollEnabled = true) {
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 10.dp)
                    ) {
                        TextField(
                            value = userName.value,
                            onValueChange = { settingsViewModel.onUserNameChanged(it) },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    settingsViewModel.onSaveSettings()
                                    focusRequester.freeFocus()
                                }
                            ),
                            modifier = Modifier
                                .focusRequester(focusRequester)
                        )

                        Button(modifier = Modifier, onClick = {
                            settingsViewModel.onSaveSettings()
                        }) {
                            Text(text = "Save")
                        }
                    }
                }
            }
        }
    }
}

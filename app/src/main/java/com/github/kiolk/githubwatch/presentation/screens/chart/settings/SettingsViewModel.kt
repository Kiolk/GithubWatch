package com.github.kiolk.githubwatch.presentation.screens.chart.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kiolk.githubwatch.data.settings.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsRepository: SettingsRepository) : ViewModel() {

    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    init {
        fetchSettings()
    }

    private fun fetchSettings() {
        viewModelScope.launch {
            _userName.value = settingsRepository.getUserName()
        }
    }

    fun onUserNameChanged(userName: String) {
        _userName.value = userName
    }

    fun onSaveSettings() {
        viewModelScope.launch {
            settingsRepository.setUserName(_userName.value)
        }
    }
}

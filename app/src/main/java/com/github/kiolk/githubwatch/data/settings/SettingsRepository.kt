package com.github.kiolk.githubwatch.data.settings

interface SettingsRepository {

    suspend fun getUserName(): String

    suspend fun setUserName(userName: String)
}

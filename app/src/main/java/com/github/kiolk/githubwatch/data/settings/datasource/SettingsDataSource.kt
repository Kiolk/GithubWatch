package com.github.kiolk.githubwatch.data.settings.datasource

interface SettingsDataSource {

    suspend fun getUserName(): String

    suspend fun setUserName(userName: String)
}

package com.github.kiolk.githubwatch.data.settings.datasource

import com.github.kiolk.githubwatch.data.settings.models.AccessTokenModel

interface SettingsDataSource {

    suspend fun getUserName(): String

    suspend fun setUserName(userName: String)

    suspend fun getAccessToken(): AccessTokenModel

    suspend fun setAccessToken(accessToken: AccessTokenModel)
}

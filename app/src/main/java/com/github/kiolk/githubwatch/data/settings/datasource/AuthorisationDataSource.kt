package com.github.kiolk.githubwatch.data.settings.datasource

import com.github.kiolk.githubwatch.data.settings.models.AccessTokenModel
import com.github.kiolk.githubwatch.data.settings.models.InstallationModel

interface AuthorisationDataSource {
    suspend fun getAvailableInstallations(): InstallationModel

    suspend fun getAccessToken(installationId: Long): AccessTokenModel
}

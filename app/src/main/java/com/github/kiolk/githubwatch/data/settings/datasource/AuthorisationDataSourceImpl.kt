package com.github.kiolk.githubwatch.data.settings.datasource

import com.github.kiolk.githubwatch.data.settings.models.AccessTokenModel
import com.github.kiolk.githubwatch.data.settings.models.InstallationModel

class AuthorisationDataSourceImpl(private val authorisationApi: AuthorisationApi) :
    AuthorisationDataSource {
    override suspend fun getAvailableInstallations(): InstallationModel {
        return authorisationApi.getAvailableInstallations()
    }

    override suspend fun getAccessToken(installationId: Long): AccessTokenModel {
        return authorisationApi.getAccessToken(installationId)
    }
}

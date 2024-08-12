package com.github.kiolk.githubwatch.data.settings

import com.github.kiolk.githubwatch.BuildConfig
import com.github.kiolk.githubwatch.data.settings.datasource.AuthorisationDataSource
import com.github.kiolk.githubwatch.data.settings.datasource.SettingsDataSource
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

class AuthorisationRepositoryImpl(
    private val settingsDataSource: SettingsDataSource,
    private val authorisationDataSource: AuthorisationDataSource
) : AuthorisationRepository {

    override suspend fun getAccessToken(): String {
        val token = settingsDataSource.getAccessToken()

        if (BuildConfig.DEBUG) {
            return BuildConfig.PRIVATE_ACCESS_TOKEN
        }

        return if (token.token.isEmpty() || isTokenExpired(token.expiresAt)) {
            val installationModel = authorisationDataSource.getAvailableInstallations()
            val accessTokenModel = authorisationDataSource.getAccessToken(installationModel.id)
            settingsDataSource.setAccessToken(accessTokenModel)

            accessTokenModel.token
        } else {
            token.token
        }
    }

    private fun isTokenExpired(expiresAt: String): Boolean {
        val expirationDate = Instant.parse(expiresAt)
        val currentTime = Clock.System.now()

        return expirationDate < currentTime
    }
}

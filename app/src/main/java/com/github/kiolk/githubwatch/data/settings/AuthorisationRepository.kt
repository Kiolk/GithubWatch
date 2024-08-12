package com.github.kiolk.githubwatch.data.settings

interface AuthorisationRepository {

    suspend fun getAccessToken(): String
}

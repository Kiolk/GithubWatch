package com.github.kiolk.githubwatch.data.settings.datasource

import android.content.SharedPreferences

// TODO added logic for store settings in encrypted variant
class LocalSettingsDataSource(private val sharedPreferences: SharedPreferences) :
    SettingsDataSource {

    override suspend fun getUserName(): String {
        return sharedPreferences.getString(USER_NAME, "").orEmpty()
    }

    override suspend fun setUserName(userName: String) {
        sharedPreferences.edit().putString(USER_NAME, userName).apply()
    }

    private companion object {
        private const val USER_NAME = "user_name"
    }
}

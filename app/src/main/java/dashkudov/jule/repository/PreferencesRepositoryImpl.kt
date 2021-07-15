package dashkudov.jule.repository

import android.content.SharedPreferences
import dashkudov.jule.api.request.auth.AuthRequest
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val preferences: SharedPreferences
): PreferencesRepository {

    private companion object {
        const val KEY_TOKEN = "pwjkbf"
        const val KEY_LOGIN = "p4emeng"
        const val KEY_PASSWORD = "wQPMFC"
    }

    override suspend fun saveToken(token: String) {
            preferences.edit().putString(KEY_TOKEN, token).apply()
    }

    override suspend fun getSavedToken(): String? {
        return preferences.getString(KEY_TOKEN, null)
    }

    override fun getSavedTokenSync(): String? {
        return preferences.getString(KEY_TOKEN, null)
    }

    override suspend fun saveAuthRequest(authRequest: AuthRequest) {
        preferences.edit().putString(KEY_LOGIN, authRequest.login).apply()
        preferences.edit().putString(KEY_PASSWORD, authRequest.password).apply()
    }

    override suspend fun getAuthRequest(): AuthRequest? {
        val login = preferences.getString(KEY_LOGIN, null)
        val password = preferences.getString(KEY_PASSWORD, null)
        return if (login == null || password == null) {
            null
        } else {
            AuthRequest(login, password)
        }
    }
}
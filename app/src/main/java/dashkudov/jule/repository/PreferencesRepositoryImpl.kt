package dashkudov.jule.repository


import dashkudov.jule.repository.PreferencesRepository
import android.content.SharedPreferences
import dashkudov.jule.api.request.auth.AuthRequest
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val preferences: SharedPreferences
): PreferencesRepository {

    override suspend fun getSavedTokenLifetime(): Int? {
        return with(preferences.getInt(KEY_TOKEN_LIFETIME, DEFAULT_LIFETIME)) {
            if (this == DEFAULT_LIFETIME) null else this
        }
    }

    override suspend fun saveTokenLifetime(tokenLifetime: Int) {
        preferences.edit().putInt(KEY_TOKEN_LIFETIME, tokenLifetime).apply()
    }

    override suspend fun saveAccessToken(token: String) {
        preferences.edit().putString(KEY_ACCESS_TOKEN, token).apply()
    }

    override suspend fun saveRefreshToken(token: String) {
        preferences.edit().putString(KEY_REFRESH_TOKEN, token).apply()
    }

    override suspend fun getSavedAccessToken(): String? {
        return preferences.getString(KEY_ACCESS_TOKEN, null)
    }

    override suspend fun getSavedRefreshToken(): String? {
        return preferences.getString(KEY_REFRESH_TOKEN, null)
    }

    override fun getSavedTokenSync(): String? {
        return preferences.getString(KEY_ACCESS_TOKEN, null)
    }

    private companion object {
        const val KEY_ACCESS_TOKEN = "pwjkbf"
        const val KEY_REFRESH_TOKEN = "wj[vpouhw"
        const val KEY_LOGIN = "p4emeng"
        const val KEY_PASSWORD = "wQPMFC"
        const val KEY_TOKEN_LIFETIME = "wegwqeg"
        const val DEFAULT_LIFETIME = -1
    }
}
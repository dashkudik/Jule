package dashkudov.jule.repository

import android.content.SharedPreferences
import dashkudov.jule.api.request.auth.AuthRequest
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val preferences: SharedPreferences
): PreferencesRepository {
    override suspend fun saveToken(token: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getSavedToken(): String? {
        TODO("Not yet implemented")
    }

    override suspend fun saveAuthRequest(authRequest: AuthRequest) {
        TODO("Not yet implemented")
    }

    override suspend fun getAuthRequest(): AuthRequest? {
        return AuthRequest("qwerty", "123")
    }
}
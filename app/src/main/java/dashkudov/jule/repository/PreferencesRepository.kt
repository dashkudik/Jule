package dashkudov.jule.repository

import dashkudov.jule.api.request.auth.AuthRequest

interface PreferencesRepository {

    // Auth
    suspend fun saveAccessToken(token: String)
    suspend fun saveRefreshToken(token: String)
    suspend fun saveTokenLifetime(tokenLifetime: Int)

    suspend fun getSavedAccessToken(): String?
    suspend fun getSavedRefreshToken(): String?
    suspend fun getSavedTokenLifetime(): Int?
    fun getSavedTokenSync(): String?
}
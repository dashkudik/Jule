package dashkudov.jule.repository

import dashkudov.jule.api.request.auth.AuthRequest

interface PreferencesRepository {

    // Auth
    suspend fun saveToken(token: String)
    suspend fun getSavedToken(): String?
    fun getSavedTokenSync(): String?
    suspend fun saveAuthRequest(authRequest: AuthRequest)
    suspend fun getAuthRequest(): AuthRequest?
}
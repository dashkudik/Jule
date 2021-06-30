package dashkudov.jule.repository

import dashkudov.jule.api.request.auth.AuthRequest

interface PreferencesRepository {

    // Auth
    suspend fun saveToken(token: String)
    suspend fun getSavedToken(): String?
    suspend fun saveLoginRequest(authRequest: AuthRequest)
    suspend fun getLoginRequest(): AuthRequest?
}
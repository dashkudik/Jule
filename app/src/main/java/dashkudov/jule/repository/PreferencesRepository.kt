package dashkudov.jule.repository

import dashkudov.jule.api.request.auth.LoginRequest

interface PreferencesRepository {

    // Auth
    suspend fun saveToken(token: String)
    suspend fun getSavedToken(): String?
    suspend fun saveLoginRequest(loginRequest: LoginRequest)
    suspend fun getLoginRequest(): LoginRequest?
}
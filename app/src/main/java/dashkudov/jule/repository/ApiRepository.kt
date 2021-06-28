package dashkudov.jule.repository

import dashkudov.jule.api.request.auth.LoginRequest
import dashkudov.jule.api.response.auth.AuthResponse

interface ApiRepository {
    suspend fun login(loginRequest: LoginRequest): AuthResponse
}
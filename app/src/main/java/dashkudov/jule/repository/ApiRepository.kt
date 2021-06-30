package dashkudov.jule.repository

import dashkudov.jule.api.request.auth.AuthRequest
import dashkudov.jule.api.response.auth.AuthResponse

interface ApiRepository {
    suspend fun login(authRequest: AuthRequest): AuthResponse
}
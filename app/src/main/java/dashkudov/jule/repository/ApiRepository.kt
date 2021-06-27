package dashkudov.jule.repository

import dashkudov.jule.api.ApiResponse
import dashkudov.jule.api.request.auth.LoginRequest
import dashkudov.jule.api.response.auth.LoginResponse

interface ApiRepository {
    suspend fun login(loginRequest: LoginRequest): LoginResponse
}
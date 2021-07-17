package dashkudov.jule.repository

import dashkudov.jule.api.ApiResponse
import dashkudov.jule.api.request.auth.AuthRequest
import dashkudov.jule.api.request.auth.ImplicitAuthRequest
import dashkudov.jule.api.request.sign_up.SignUpRequest
import dashkudov.jule.api.response.auth.AuthResponse

interface ApiRepository {

    // Auth
    suspend fun auth(authRequest: AuthRequest): ApiResponse<AuthResponse>
    suspend fun implicitAuth(implicitAuthRequest: ImplicitAuthRequest): ApiResponse<AuthResponse>

    // Sing up
    suspend fun signUp(signUpRequest: SignUpRequest): ApiResponse<AuthResponse>
}
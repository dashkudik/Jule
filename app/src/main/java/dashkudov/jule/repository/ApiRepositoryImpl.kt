package dashkudov.jule.repository

import dashkudov.jule.api.ApiResponse
import dashkudov.jule.api.request.auth.AuthRequest
import dashkudov.jule.api.request.auth.ImplicitAuthRequest
import dashkudov.jule.api.request.sign_up.SignUpRequest
import dashkudov.jule.api.response.auth.AuthResponse
import dashkudov.jule.data_sources.ApiDataSource
import kotlinx.coroutines.delay

class ApiRepositoryImpl(private val apiRepository: ApiDataSource): ApiRepository {

    override suspend fun auth(authRequest: AuthRequest): ApiResponse<AuthResponse> {
        return apiRepository.authAsync(authRequest).await()
    }

    override suspend fun implicitAuth(implicitAuthRequest: ImplicitAuthRequest): ApiResponse<AuthResponse> {
        return apiRepository.implicitAuthAsync(implicitAuthRequest).await()
    }

    override suspend fun signUp(signUpRequest: SignUpRequest): ApiResponse<AuthResponse> {
        return apiRepository.signUpAsync(signUpRequest).await()
    }
}
package dashkudov.jule.repository

import dashkudov.jule.api.ApiResponse
import dashkudov.jule.api.request.auth.AuthRequest
import dashkudov.jule.api.response.auth.AuthResponse
import dashkudov.jule.data_sources.ApiDataSource

class ApiRepositoryImpl(private val apiRepository: ApiDataSource): ApiRepository {

    override suspend fun auth(authRequest: AuthRequest): ApiResponse<AuthResponse> {
        return apiRepository.authAsync(authRequest).await()
    }
}
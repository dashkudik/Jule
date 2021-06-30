package dashkudov.jule.repository

import dashkudov.jule.api.request.auth.AuthRequest
import dashkudov.jule.api.response.auth.AuthResponse
import dashkudov.jule.dataSources.ApiDataSource

class ApiRepositoryImpl(private val apiRepository: ApiDataSource): ApiRepository {

    override suspend fun login(authRequest: AuthRequest): AuthResponse {
        return apiRepository.authAsync(authRequest).await().data
    }
}
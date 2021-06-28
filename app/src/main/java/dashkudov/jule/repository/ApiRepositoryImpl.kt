package dashkudov.jule.repository

import dashkudov.jule.api.request.auth.LoginRequest
import dashkudov.jule.api.response.auth.AuthResponse
import dashkudov.jule.dataSources.ApiDataSource

class ApiRepositoryImpl(private val apiRepository: ApiDataSource): ApiRepository {

    override suspend fun login(loginRequest: LoginRequest): AuthResponse {
        return apiRepository.loginAsync(loginRequest).await().data
    }
}
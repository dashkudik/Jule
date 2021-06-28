package dashkudov.jule.dataSources

import dashkudov.jule.Config.API_HOST
import dashkudov.jule.api.ApiResponse
import dashkudov.jule.api.request.auth.LoginRequest
import dashkudov.jule.api.response.auth.AuthResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET

interface ApiDataSource {
    @GET("$API_HOST/auth/token")
    fun loginAsync(@Body content: LoginRequest): Deferred<ApiResponse<AuthResponse>>
}
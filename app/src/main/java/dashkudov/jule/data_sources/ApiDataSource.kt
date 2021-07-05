package dashkudov.jule.data_sources

import dashkudov.jule.common.Config.API_HOST
import dashkudov.jule.api.ApiResponse
import dashkudov.jule.api.request.auth.AuthRequest
import dashkudov.jule.api.response.auth.AuthResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiDataSource {
    @POST("$API_HOST/auth/token")
    fun authAsync(@Body content: AuthRequest): Deferred<ApiResponse<AuthResponse>>
}
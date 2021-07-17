package dashkudov.jule.data_sources

import dashkudov.jule.common.Config.API_HOST
import dashkudov.jule.api.ApiResponse
import dashkudov.jule.api.request.auth.AuthRequest
import dashkudov.jule.api.request.auth.ImplicitAuthRequest
import dashkudov.jule.api.request.sign_up.SignUpRequest
import dashkudov.jule.api.response.auth.AuthResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiDataSource {

    // Auth

    @POST("${API_HOST}users/login")
    fun authAsync(@Body content: AuthRequest): Deferred<ApiResponse<AuthResponse>>

    @POST("${API_HOST}users/refresh/token")
    fun implicitAuthAsync(@Body implicitAuthRequest: ImplicitAuthRequest): Deferred<ApiResponse<AuthResponse>>

    // Sign up

    @POST("${API_HOST}users/register")
    fun signUpAsync(@Body signUpRequest: SignUpRequest): Deferred<ApiResponse<AuthResponse>>

}
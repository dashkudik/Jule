package dashkudov.jule.api.response.auth

import dashkudov.jule.api.ApiResponse
import dashkudov.jule.api.model.ProfileApi

data class AuthResponse(
    val accessToken: String,
    val profile: ProfileApi
)

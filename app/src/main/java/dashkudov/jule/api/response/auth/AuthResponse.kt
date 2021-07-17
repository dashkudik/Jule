package dashkudov.jule.api.response.auth

import dashkudov.jule.api.ApiResponse
import dashkudov.jule.api.model.ProfileApi
import dashkudov.jule.api.model.TokenInfoApi

data class AuthResponse(
    val tokens: TokenInfoApi,
    val profile: ProfileApi
)

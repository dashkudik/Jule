package dashkudov.jule.api.model

data class TokenInfoApi(
    val accessToken: String,
    val accessTokenLifeMinutes: Int,
    val refreshToken: String,
)

package dashkudov.jule.api.model

data class ProfileApi(
    val login: String,
    val name: String,
    val id: Int,
    val avatarUrl: String?,
)
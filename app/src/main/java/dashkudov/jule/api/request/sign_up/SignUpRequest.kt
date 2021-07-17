package dashkudov.jule.api.request.sign_up

import dashkudov.jule.api.model.GenderApi

data class SignUpRequest(
    val login: String,
    val password: String,
    val email: String,
    val name: String,
    val gender: GenderApi?
)

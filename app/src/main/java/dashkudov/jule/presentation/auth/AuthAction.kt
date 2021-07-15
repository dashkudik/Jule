package dashkudov.jule.presentation.auth

import dashkudov.jule.api.request.auth.AuthRequest
import dashkudov.jule.api.response.auth.AuthResponse
import dashkudov.jule.model.InterpretedError
import dashkudov.jule.mvi.Action

sealed class AuthAction: Action {
    data class Auth(val authRequest: AuthRequest): AuthAction()

    // Effects
    data class AuthDone(
        val authRequest: AuthRequest,
        val authResponse: AuthResponse? = null,
        val interpretedError: InterpretedError? = null,
    ): AuthAction()
}

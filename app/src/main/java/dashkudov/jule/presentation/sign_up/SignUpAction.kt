package dashkudov.jule.presentation.sign_up

import dashkudov.jule.api.request.sign_up.SignUpRequest
import dashkudov.jule.api.response.auth.AuthResponse
import dashkudov.jule.model.InterpretedError
import dashkudov.jule.mvi.Action

sealed class SignUpAction: Action {
    data class SignUp(val signUpRequest: SignUpRequest): SignUpAction()

    // Effects
    data class SignUpDone(
        val signUpResponse: AuthResponse? = null,
        val interpretedError: InterpretedError? = null
    ) : SignUpAction()
}

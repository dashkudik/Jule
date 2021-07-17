package dashkudov.jule.presentation.sign_up

import dashkudov.jule.api.request.sign_up.SignUpRequest
import dashkudov.jule.mvi.Action

sealed class SignUpAction: Action {
    data class SignUp(val signUpRequest: SignUpRequest)
}

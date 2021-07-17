package dashkudov.jule.presentation.sign_up

import androidx.navigation.NavDirections
import dashkudov.jule.mvi.State
import dashkudov.jule.presentation.auth.AuthState

sealed class SignUpState: State {
    data class Default(
        val navDirections: NavDirections? = null,
    ): SignUpState()

    object Loading: SignUpState()
}

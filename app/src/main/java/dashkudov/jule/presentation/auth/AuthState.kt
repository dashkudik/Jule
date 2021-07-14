package dashkudov.jule.presentation.auth

import androidx.navigation.NavDirections
import dashkudov.jule.mvi.State
import dashkudov.jule.presentation.start.StartState


sealed class AuthState: State {
    data class Default(val navDirections: NavDirections? = null): AuthState()
}

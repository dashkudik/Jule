package dashkudov.jule.presentation.start

import androidx.navigation.NavDirections
import dashkudov.jule.mvi.State


sealed class StartState: State {
    data class Default(val navDirections: NavDirections? = null): StartState()
}

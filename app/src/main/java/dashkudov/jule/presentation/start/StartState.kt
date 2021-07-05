package dashkudov.jule.presentation.start

import dashkudov.jule.mvi.State

sealed class StartState: State {
    object Shown: StartState()
    object ToFeed: StartState()
    data class ToAuth(val message: String? = null): StartState()
    data class Error(val message: String): StartState()
}

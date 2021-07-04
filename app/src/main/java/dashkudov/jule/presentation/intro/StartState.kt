package dashkudov.jule.presentation.intro

import dashkudov.jule.mvi.State

sealed class StartState: State {
    object Shown: StartState()
    object OnFeed: StartState()
    object OnAuth: StartState()
    data class Error(val message: String): StartState()
}

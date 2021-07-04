package dashkudov.jule.presentation.intro

import dashkudov.jule.mvi.Action

sealed class StartAction: Action {
    object ImplicitAuth: StartAction()

    // Side effects
    object OnAuth: StartAction()
    object OnFeed: StartAction()
    data class OnError(val message: String): StartAction()
}

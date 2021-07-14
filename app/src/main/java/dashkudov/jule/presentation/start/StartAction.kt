package dashkudov.jule.presentation.start

import dashkudov.jule.model.ApiErrorModel
import dashkudov.jule.model.InterpretedError
import dashkudov.jule.model.LocalErrorModel
import dashkudov.jule.mvi.Action

sealed class StartAction: Action {
    object ImplicitAuth: StartAction()

    // Effects

    data class ImplicitAuthDone(
        val interpretedError: InterpretedError? = null,
    ): StartAction()

    object ImplicitAuthImpossible: StartAction()
}

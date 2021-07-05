package dashkudov.jule.presentation.start

import dashkudov.jule.model.ErrorModel
import dashkudov.jule.mvi.Action

sealed class StartAction: Action {
    object ImplicitAuth: StartAction()

    // Effects
    data class ImplicitAuthDone(val errorModel: ErrorModel? = null): StartAction()
    object ImplicitAuthImpossible: StartAction()
}

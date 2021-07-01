package dashkudov.jule.presentation.intro

import dashkudov.jule.mvi.Action

sealed class IntroAction: Action {
    object Completed: IntroAction()
}

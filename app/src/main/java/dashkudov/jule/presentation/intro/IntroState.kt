package dashkudov.jule.presentation.intro

import dashkudov.jule.mvi.State

sealed class IntroState: State {
    object Shown: IntroState()
}

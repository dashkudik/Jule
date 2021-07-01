package dashkudov.jule.presentation.intro

import dashkudov.jule.mvi.Reducer

class IntroReducer: Reducer<IntroState, IntroAction> {
    override fun reduce(state: IntroState, action: IntroAction): IntroState {
        return IntroState.Shown
    }
}
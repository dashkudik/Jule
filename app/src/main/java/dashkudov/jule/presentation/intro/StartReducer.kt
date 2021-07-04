package dashkudov.jule.presentation.intro

import dashkudov.jule.mvi.Reducer
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class StartReducer(middleware: StartImplicitAuthMiddleware): Reducer<StartState, StartAction>(middleware) {
    override fun reduce(state: StartState, action: StartAction): StartState {
        return when (action) {
            is StartAction.OnFeed -> StartState.OnFeed
            is StartAction.OnAuth -> StartState.OnAuth
            is StartAction.ImplicitAuth -> StartState.Shown
            is StartAction.OnError -> StartState.Error(action.message)
        }
    }
}
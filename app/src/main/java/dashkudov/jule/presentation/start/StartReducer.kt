package dashkudov.jule.presentation.start

import android.util.Log
import dashkudov.jule.api.ResponseStatus
import dashkudov.jule.mvi.Reducer

class StartReducer(middleware: StartImplicitAuthMiddleware): Reducer<StartState, StartAction>(middleware) {
    override fun reduce(state: StartState, action: StartAction): StartState {
        Log.d("TEST", "Reducer received action ${action.javaClass.simpleName} ")
        Log.d("TEST", "Reducer received state ${state.javaClass.simpleName} ")
        return when (action) {
            is StartAction.ImplicitAuth -> StartState.Shown
            is StartAction.ImplicitAuthDone -> {
                action.errorModel?.let {
                    StartState.ToAuth(it.message)
                } ?: StartState.ToFeed
            }
            is StartAction.ImplicitAuthImpossible -> StartState.ToAuth()
        }
    }
}
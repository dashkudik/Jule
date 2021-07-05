package dashkudov.jule.presentation.start

import android.util.Log
import dashkudov.jule.api.ResponseStatus
import dashkudov.jule.model.JuleLogger
import dashkudov.jule.mvi.Reducer
import javax.inject.Inject

class StartReducer @Inject constructor(logger: JuleLogger, middleware: StartImplicitAuthMiddleware):
        Reducer<StartState, StartAction>(logger, middleware) {
    override fun reduce(state: StartState, action: StartAction): StartState {
        logger.connect(javaClass)
        logger.log("State - ${state.javaClass.simpleName} | Action = ${action.javaClass.simpleName}")
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
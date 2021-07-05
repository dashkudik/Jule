package dashkudov.jule.presentation.start

import dashkudov.jule.mvi.Reducer

class StartReducer(middleware: StartImplicitAuthMiddleware):
        Reducer<StartState, StartAction>(middleware) {

    override fun reduce(state: StartState, action: StartAction): StartState? {
        return when (action) {
            is StartAction.ImplicitAuthDone -> {
                when {
                    action.apiErrorModel != null -> {
                        StartState.ToAuth(action.apiErrorModel.message)
                    }
                    action.localErrorModel != null -> {
                        StartState.ToAuth(action.localErrorModel.localError.name)
                    }
                    else -> StartState.ToFeed
                }
            }
            is StartAction.ImplicitAuthImpossible -> StartState.ToAuth()
            else -> null
        }
    }
}
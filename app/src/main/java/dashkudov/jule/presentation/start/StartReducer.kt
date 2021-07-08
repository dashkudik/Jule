package dashkudov.jule.presentation.start

import dashkudov.jule.mvi.Reducer

class StartReducer: Reducer<StartState, StartAction> {

    override fun reduce(state: StartState, action: StartAction): StartState? {
        return when (action) {
            is StartAction.ImplicitAuthDone -> {
                with(action.interpretedError?.userFriendlyInterpretation) {
                    if (this != null) {
                        StartState.ToAuth(this)
                    } else  {
                        StartState.ToFeed
                    }
                }
            }
            is StartAction.ImplicitAuthImpossible -> {
                StartState.ToAuth()
            }
            else -> null
        }
    }
}
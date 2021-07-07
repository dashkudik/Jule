package dashkudov.jule.presentation.start

import dashkudov.jule.mvi.Reducer

class StartReducer(middleware: ImplicitAuthMiddleware, _middleware: LogoAnimationSuspenseMiddleware):
        Reducer<StartState, StartAction>(middleware, _middleware) {

    override fun reduce(state: StartState, action: StartAction): StartState? {
        return when (action) {
            is StartAction.LogoAnimationSuspenseRequired -> StartState.LogoAnimating
            is StartAction.ImplicitAuthDone -> StartState.ToAuth(action.interpretedError?.userFriendlyInterpretation)
            is StartAction.ImplicitAuthImpossible -> StartState.ToAuth()
            else -> null
        }
    }
}
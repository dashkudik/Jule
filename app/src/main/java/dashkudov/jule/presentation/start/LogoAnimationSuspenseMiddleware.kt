package dashkudov.jule.presentation.start

import dashkudov.jule.common.ViewExtensions.LOGO_FADE_IN_TIME
import dashkudov.jule.mvi.Middleware
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class LogoAnimationSuspenseMiddleware: Middleware<StartAction>() {

    override fun bind(actions: Flow<StartAction>): Flow<StartAction> {
        return flow {
            actions.filter { it is StartAction.LogoAnimationSuspenseRequired }.collect {
                delay(LOGO_FADE_IN_TIME)
                emit(StartAction.ImplicitAuth)
            }
        }
    }
}
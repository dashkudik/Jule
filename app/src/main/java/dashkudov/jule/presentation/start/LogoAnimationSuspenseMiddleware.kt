package dashkudov.jule.presentation.start

import android.util.Log
import dashkudov.jule.common.ViewExtensions.LOGO_FADE_IN_TIME
import dashkudov.jule.mvi.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class LogoAnimationSuspenseMiddleware: Middleware<StartAction>() {

    override suspend fun effect(action: StartAction): StartAction? {
        return if (action is StartAction.LogoAnimationSuspenseRequired) {
            delay(LOGO_FADE_IN_TIME)
            StartAction.ImplicitAuth
        } else null
    }
}
package dashkudov.jule.presentation.start

import dashkudov.jule.common.MapErrorUtil.doRequest
import dashkudov.jule.common.MapErrorUtil.extractLocalError
import dashkudov.jule.mvi.Middleware
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.IllegalStateException

class ImplicitAuthMiddleware: Middleware<StartAction>() {

    override suspend fun effect(action: StartAction): StartAction? {
        var effect: StartAction? = null
        if (action is StartAction.ImplicitAuth) {
                val lastKnownAuthRequest = preferencesRepository.getAuthRequest()
                if (lastKnownAuthRequest != null) {
                    doRequest(
                            responseAsync = {
                                apiRepository.auth(lastKnownAuthRequest)
                            },
                            onOk = {
                                effect = StartAction.ImplicitAuthDone()
                            },
                            onApiErrorStatus = {
                                effect = StartAction.ImplicitAuthDone(interpretedError = this)
                            },
                            onException = {
                                effect = StartAction.ImplicitAuthDone(interpretedError = extractLocalError())
                            },
                    )
                } else {
                    effect = StartAction.ImplicitAuthImpossible
                }
            }
        return effect
    }
}
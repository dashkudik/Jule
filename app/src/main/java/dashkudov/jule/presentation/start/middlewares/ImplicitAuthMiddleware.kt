package dashkudov.jule.presentation.start.middlewares

import dashkudov.jule.common.MapErrorUtil.doRequest
import dashkudov.jule.common.MapErrorUtil.extractLocalError
import dashkudov.jule.mvi.Middleware
import dashkudov.jule.mvi.Store
import dashkudov.jule.presentation.start.StartAction

class ImplicitAuthMiddleware(store: Store<*, *, *>): Middleware<StartAction>(store) {

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
                                effect = StartAction.ImplicitAuthDone(
                                    authRequest = lastKnownAuthRequest,
                                    authResponse = this
                                )
                            },
                            onApiErrorStatus = {
                                effect = StartAction.ImplicitAuthDone(
                                    authRequest = lastKnownAuthRequest,
                                    interpretedError = this
                                )
                            },
                            onException = {
                                effect = StartAction.ImplicitAuthDone(
                                    authRequest = lastKnownAuthRequest,
                                    interpretedError = extractLocalError()
                                )
                            },
                    )
                } else {
                    effect = StartAction.ImplicitAuthImpossible
                }
            }
        return effect
    }
}
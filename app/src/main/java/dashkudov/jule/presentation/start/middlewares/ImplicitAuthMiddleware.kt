package dashkudov.jule.presentation.start.middlewares

import dashkudov.jule.api.request.auth.ImplicitAuthRequest
import dashkudov.jule.common.MapErrorUtil.extractLocalError
import dashkudov.jule.mvi.Middleware
import dashkudov.jule.mvi.Store
import dashkudov.jule.presentation.start.StartAction
import javax.inject.Inject

class ImplicitAuthMiddleware(store: Store<*, *, *>): Middleware<StartAction>(store) {

    override suspend fun effect(action: StartAction): StartAction? {
        var effect: StartAction? = null
        if (action is StartAction.ImplicitAuth) {
                val lastKnownToken = preferencesRepository.getSavedRefreshToken()
                if (lastKnownToken != null) {
                    doRequest(
                            responseAsync = {
                                apiRepository.implicitAuth(
                                    implicitAuthRequest = ImplicitAuthRequest(lastKnownToken)
                                )
                            },
                            onOk = {
                                effect = StartAction.ImplicitAuthDone()
                            },
                            onApiErrorStatus = {
                                effect = StartAction.ImplicitAuthDone(
                                    interpretedError = this
                                )
                            },
                            onException = {
                                effect = StartAction.ImplicitAuthDone(
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
package dashkudov.jule.presentation.auth.middlewares

import dashkudov.jule.common.MapErrorUtil.doRequest
import dashkudov.jule.common.MapErrorUtil.extractLocalError
import dashkudov.jule.mvi.Middleware
import dashkudov.jule.mvi.Store
import dashkudov.jule.presentation.auth.AuthAction

class AuthMiddleware(store: Store<*, *, *>): Middleware<AuthAction>(store) {
    override suspend fun effect(action: AuthAction): AuthAction? {
        var effect: AuthAction? = null
        when (action) {
            is AuthAction.Auth -> {
                doRequest(
                    responseAsync = {
                        apiRepository.auth(action.authRequest)
                    },
                    onOk = {
                        effect = AuthAction.AuthDone()
                    },
                    onApiErrorStatus = {
                        effect = AuthAction.AuthDone(interpretedError = this)
                    },
                    onException = {
                        effect = AuthAction.AuthDone(interpretedError = extractLocalError())
                    },
                )
            }
            else -> effect = null
        }
        return effect
    }
}
package dashkudov.jule.presentation.auth.middlewares

import dashkudov.jule.api.ResponseStatus
import dashkudov.jule.common.MapErrorUtil.extractLocalError
import dashkudov.jule.mvi.Middleware
import dashkudov.jule.mvi.Store
import dashkudov.jule.presentation.auth.AuthAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthMiddleware(store: Store<*, *, *>): Middleware<AuthAction>(store) {
    override suspend fun effect(action: AuthAction): AuthAction? {
        var effect: AuthAction? = null
        with(action) {
            if (this is AuthAction.Auth) {
                doRequest(
                    responseAsync = {
                        apiRepository.auth(authRequest)
                    },
                    onOk = {
                        effect = AuthAction.AuthDone(
                            authRequest = authRequest,
                            authResponse = this
                        )
                        CoroutineScope(Dispatchers.IO).launch {
                            preferencesRepository.saveAuthRequest(authRequest)
                        }
                    },
                    onApiErrorStatus = {
                        effect = AuthAction.AuthDone(
                            authRequest = authRequest,
                            interpretedError = this
                        )
                    },
                    onException = {
                        effect = AuthAction.AuthDone(
                            authRequest = authRequest,
                            interpretedError = extractLocalError()
                        )
                    },
                )
            }
        }
        return effect
    }
}
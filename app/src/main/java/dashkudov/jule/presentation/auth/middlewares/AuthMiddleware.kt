package dashkudov.jule.presentation.auth.middlewares

import dashkudov.jule.api.ResponseStatus
import dashkudov.jule.common.MapErrorUtil.extractLocalError
import dashkudov.jule.mvi.Middleware
import dashkudov.jule.mvi.Store
import dashkudov.jule.presentation.auth.AuthAction
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

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
                        withContext(coroutineContext) { preferencesRepository.saveRefreshToken(tokens.refreshToken) }
                        withContext(coroutineContext) { preferencesRepository.saveAccessToken(tokens.accessToken) }
                        withContext(coroutineContext) { preferencesRepository.saveTokenLifetime(tokens.accessTokenLifeMinutes) }
                        effect = AuthAction.AuthDone(
                            authRequest = authRequest,
                            authResponse = this
                        )
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
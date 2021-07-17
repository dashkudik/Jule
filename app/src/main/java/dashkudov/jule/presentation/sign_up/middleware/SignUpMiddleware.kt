package dashkudov.jule.presentation.sign_up.middleware

import dashkudov.jule.api.request.auth.ImplicitAuthRequest
import dashkudov.jule.api.request.sign_up.SignUpRequest
import dashkudov.jule.common.MapErrorUtil.extractLocalError
import dashkudov.jule.mvi.Middleware
import dashkudov.jule.mvi.Store
import dashkudov.jule.presentation.sign_up.SignUpAction
import dashkudov.jule.presentation.start.StartAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpMiddleware(store: Store<*, *, *>): Middleware<SignUpAction>(store) {

    override suspend fun effect(action: SignUpAction): SignUpAction? {
        var effect: SignUpAction? = null
        with(action) {
            if (this is SignUpAction.SignUp) {
                doRequest(
                    responseAsync = {
                        apiRepository.signUp(
                            signUpRequest = signUpRequest
                        )
                    },
                    onOk = {
                        CoroutineScope(Dispatchers.IO).launch {
                            preferencesRepository.saveRefreshToken(tokens.refreshToken)
                            preferencesRepository.saveAccessToken(tokens.accessToken)
                            preferencesRepository.saveTokenLifetime(tokens.accessTokenLifeMinutes)
                        }
                        effect = SignUpAction.SignUpDone(
                            signUpResponse = this
                        )
                    },
                    onApiErrorStatus = {
                        effect = SignUpAction.SignUpDone(
                            interpretedError = this
                        )
                    },
                    onException = {
                        effect = SignUpAction.SignUpDone(
                            interpretedError = extractLocalError()
                        )
                    },
                )
            }
        }
        return effect
    }
}
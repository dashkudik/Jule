package dashkudov.jule.presentation.sign_up

import dashkudov.jule.model.JuleLogger
import dashkudov.jule.mvi.Store
import dashkudov.jule.presentation.auth.AuthReducer
import dashkudov.jule.presentation.auth.middlewares.AuthMiddleware
import dashkudov.jule.presentation.sign_up.middleware.SignUpMiddleware
import dashkudov.jule.repository.ApiRepository
import dashkudov.jule.repository.PreferencesRepository
import javax.inject.Inject

class SignUpStore @Inject constructor(
    apiRepository: ApiRepository,
    preferencesRepository: PreferencesRepository,
    logger: JuleLogger,
) :Store<SignUpState, SignUpAction, SignUpNews>(apiRepository, preferencesRepository, logger) {
    init {
        middlewares = listOf(SignUpMiddleware(this))
        reducer = SignUpReducer()
    }
}
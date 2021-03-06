package dashkudov.jule.presentation.auth

import dashkudov.jule.model.JuleLogger
import dashkudov.jule.mvi.Store
import dashkudov.jule.presentation.auth.middlewares.AuthMiddleware
import dashkudov.jule.repository.ApiRepository
import dashkudov.jule.repository.PreferencesRepository
import javax.inject.Inject

class AuthStore @Inject constructor(
    apiRepository: ApiRepository,
    preferencesRepository: PreferencesRepository,
    logger: JuleLogger,
): Store<AuthState, AuthAction, AuthNews>(
    apiRepository, preferencesRepository, logger
) {
    init {
        middlewares = listOf(AuthMiddleware(this))
        reducer = AuthReducer()
    }
}
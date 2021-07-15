package dashkudov.jule.presentation.auth

import dashkudov.jule.mvi.Store
import dashkudov.jule.presentation.auth.middlewares.AuthMiddleware
import dashkudov.jule.presentation.auth.middlewares.SaveAuthRequestMiddleware
import dashkudov.jule.presentation.auth.middlewares.SaveTokenMiddleware
import dashkudov.jule.repository.ApiRepository
import dashkudov.jule.repository.PreferencesRepository
import javax.inject.Inject

class AuthStore @Inject constructor(
    apiRepository: ApiRepository,
    preferencesRepository: PreferencesRepository
): Store<AuthState, AuthAction, AuthNews>(
    apiRepository, preferencesRepository
) {
    init {
        middlewares = listOf(
            AuthMiddleware(this),
            SaveAuthRequestMiddleware(this),
            SaveTokenMiddleware(this)
        )
        reducer = AuthReducer()
    }
}
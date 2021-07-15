package dashkudov.jule.presentation.auth.middlewares

import dashkudov.jule.mvi.Middleware
import dashkudov.jule.mvi.Store
import dashkudov.jule.presentation.auth.AuthAction
import dashkudov.jule.presentation.start.StartAction

class SaveAuthRequestMiddleware(store: Store<*, *, *>): Middleware<AuthAction>(store) {

    override suspend fun effect(action: AuthAction): AuthAction? {
        with (action) {
            if (this is AuthAction.AuthDone) {
                if (interpretedError == null) {
                    preferencesRepository.saveAuthRequest(authRequest)
                }
            }
        }
        return null
    }
}
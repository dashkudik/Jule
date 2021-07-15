package dashkudov.jule.presentation.auth.middlewares

import dashkudov.jule.mvi.Action
import dashkudov.jule.mvi.Middleware
import dashkudov.jule.mvi.Store
import dashkudov.jule.presentation.auth.AuthAction
import dashkudov.jule.presentation.start.StartAction
import java.lang.IllegalArgumentException

class SaveTokenMiddleware(store: Store<*, *, *>): Middleware<AuthAction>(store) {

    override suspend fun effect(action: AuthAction): AuthAction? {
        with(action) {
            if (action is AuthAction.AuthDone) {
                if (action.interpretedError == null) {
                    preferencesRepository.saveToken(action.authResponse?.accessToken
                        ?: throw IllegalArgumentException("Response is null"))
                }
            }
        }
        return null
    }
}
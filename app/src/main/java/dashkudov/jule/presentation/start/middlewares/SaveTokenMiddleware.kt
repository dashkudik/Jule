package dashkudov.jule.presentation.start.middlewares

import dashkudov.jule.mvi.Action
import dashkudov.jule.mvi.Middleware
import dashkudov.jule.mvi.Store
import dashkudov.jule.presentation.auth.AuthAction
import dashkudov.jule.presentation.start.StartAction
import java.lang.IllegalArgumentException

class SaveTokenMiddleware(store: Store<*, *, *>): Middleware<StartAction>(store) {

    override suspend fun effect(action: StartAction): StartAction? {
        when (action) {
            is StartAction.ImplicitAuthDone -> {
                if (action.interpretedError == null) {
                    preferencesRepository.saveToken(action.authResponse?.accessToken ?: throw IllegalArgumentException("Response is null"))
                }
            }
        }
        return null
    }
}
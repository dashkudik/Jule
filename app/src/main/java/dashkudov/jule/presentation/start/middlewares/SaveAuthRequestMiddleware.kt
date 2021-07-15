package dashkudov.jule.presentation.start.middlewares

import dashkudov.jule.mvi.Middleware
import dashkudov.jule.mvi.Store
import dashkudov.jule.presentation.start.StartAction

class SaveAuthRequestMiddleware(store: Store<*, *, *>): Middleware<StartAction>(store) {

    override suspend fun effect(action: StartAction): StartAction? {
        with (action) {
            if (this is StartAction.ImplicitAuthDone) {
                if (interpretedError == null) {
                    preferencesRepository.saveAuthRequest(authRequest)
                }
            }
        }
        return null
    }
}
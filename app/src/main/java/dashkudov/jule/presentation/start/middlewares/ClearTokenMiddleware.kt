package dashkudov.jule.presentation.start.middlewares

import dashkudov.jule.mvi.Action
import dashkudov.jule.mvi.Middleware
import dashkudov.jule.mvi.Store
import dashkudov.jule.presentation.auth.AuthAction
import dashkudov.jule.presentation.start.StartAction
import java.lang.IllegalArgumentException

class ClearTokenMiddleware(store: Store<*, *, *>): Middleware<AuthAction>(store) {

    override suspend fun effect(action: AuthAction): AuthAction? {
        with(action) {
            if (this is AuthAction.AuthDone) {

            }
        }
        return null
    }
}
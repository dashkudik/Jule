package dashkudov.jule.presentation.sign_up.middleware

import dashkudov.jule.mvi.Middleware
import dashkudov.jule.mvi.Store
import dashkudov.jule.presentation.sign_up.SignUpAction

class SignUpMiddleware(store: Store<*, *, *>): Middleware<SignUpAction>(store) {

    override suspend fun effect(action: SignUpAction): SignUpAction? {
        return null
    }

}
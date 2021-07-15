package dashkudov.jule.presentation.auth

import android.widget.Toast
import dashkudov.jule.mvi.Reducer
import dashkudov.jule.presentation.auth.ui.AuthFragment
import dashkudov.jule.presentation.auth.ui.AuthFragmentDirections

class AuthReducer: Reducer<AuthState, AuthAction, AuthNews> {

    override fun reduce(state: AuthState, action: AuthAction): Pair<AuthState?, AuthNews?> {
        var reducedState: AuthState? = null
        var reducedNews: AuthNews? = null
        when (action) {
            is AuthAction.AuthDone -> {
                action.interpretedError?.let {
                    reducedNews = AuthNews.Message(Toast.LENGTH_SHORT, it.userFriendlyInterpretation)
                    reducedState = AuthState.Default()
                } ?: run {
                    reducedState = AuthState.Default(AuthFragmentDirections.authFeed())
                }
            }
            is AuthAction.Auth -> {
                reducedState = AuthState.Loading
            }
        }
        return reducedState to reducedNews
    }
}
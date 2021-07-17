package dashkudov.jule.presentation.sign_up

import android.widget.Toast
import dashkudov.jule.mvi.Reducer
import dashkudov.jule.presentation.auth.AuthNews
import dashkudov.jule.presentation.auth.AuthState
import dashkudov.jule.presentation.sign_up.ui.SignUpFragmentDirections

class SignUpReducer: Reducer<SignUpState, SignUpAction, SignUpNews> {
    override fun reduce(state: SignUpState, action: SignUpAction): Pair<SignUpState?, SignUpNews?> {
        var reducedState: SignUpState? = null
        var reducedNews: SignUpNews? = null

        when (action) {
            is SignUpAction.SignUpDone -> {
                action.interpretedError?.let {
                    it.userFriendlyInterpretation.takeIf { it.isNotEmpty() }?.let {
                        reducedNews = SignUpNews.Message(Toast.LENGTH_SHORT, it)
                    }
                } ?: run {
                    reducedState = SignUpState.Default(
                        navDirections = SignUpFragmentDirections.signUpFeed()
                    )
                }
            }
        }
        return reducedState to reducedNews
    }
}
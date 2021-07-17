package dashkudov.jule.presentation.sign_up

import dashkudov.jule.mvi.Reducer
import dashkudov.jule.presentation.auth.AuthNews
import dashkudov.jule.presentation.auth.AuthState

class SignUpReducer: Reducer<SignUpState, SignUpAction, SignUpNews> {
    override fun reduce(state: SignUpState, action: SignUpAction): Pair<SignUpState?, SignUpNews?> {
        var reducedState: SignUpState? = null
        var reducedNews: SignUpNews? = null

        // ... //

        return reducedState to reducedNews
    }
}
package dashkudov.jule.presentation.start

import android.widget.Toast
import dashkudov.jule.mvi.Reducer
import dashkudov.jule.presentation.auth.AuthNews
import dashkudov.jule.presentation.auth.AuthState
import dashkudov.jule.presentation.start.ui.StartFragmentDirections

class StartReducer: Reducer<StartState, StartAction, StartNews> {

    override fun reduce(state: StartState, action: StartAction): Pair<StartState?, StartNews?> {
        var reducedState: StartState? = null
        var reducedNews: StartNews? = null
        when (action) {
            is StartAction.ImplicitAuthDone -> {
                action.interpretedError?.let {
                    reducedNews = StartNews.Message(Toast.LENGTH_SHORT, it.userFriendlyInterpretation)
                    reducedState = StartState.Default(StartFragmentDirections.startAuth())
                } ?: run {
                    reducedState = StartState.Default(StartFragmentDirections.startFeed())
                }
            }
            is StartAction.ImplicitAuthImpossible -> {
                reducedState = StartState.Default(StartFragmentDirections.startAuth())
            }
        }
        return reducedState to reducedNews
    }
}

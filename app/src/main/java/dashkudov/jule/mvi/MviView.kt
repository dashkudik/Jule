package dashkudov.jule.mvi

import kotlinx.coroutines.flow.Flow

interface MviView<A: Action, S: State> {
    fun render(state: S)
    val flow: Flow<A>
}
package dashkudov.jule.mvi

import dashkudov.jule.model.JuleLogger

interface Reducer<S: State, A: Action> {
    fun reduce(state: S, action: A): S?
    operator fun invoke(state: S, action: A) = reduce(state, action)
}
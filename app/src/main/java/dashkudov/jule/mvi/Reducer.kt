package dashkudov.jule.mvi

interface Reducer<State, Action> {
    fun reduce(state: State, action: Action): State
}
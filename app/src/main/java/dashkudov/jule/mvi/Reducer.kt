package dashkudov.jule.mvi

interface Reducer<S: State, A: Action, N: News> {
    fun reduce(state: S, action: A): Pair<S?, N?>
    operator fun invoke(state: S, action: A) = reduce(state, action)
}
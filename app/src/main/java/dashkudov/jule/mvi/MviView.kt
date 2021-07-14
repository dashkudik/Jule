package dashkudov.jule.mvi

interface MviView<S: State, N: News> {
    fun renderState(state: S)
    fun renderNews(new: N)
}
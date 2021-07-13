package dashkudov.jule.mvi

interface MviView<S: State> {
    fun render(state: S)
}
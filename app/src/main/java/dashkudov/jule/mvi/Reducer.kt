package dashkudov.jule.mvi

abstract class Reducer<S: State, A: Action>(vararg _middlewares: Middleware<A>) {

    val middlewares = _middlewares

    abstract fun reduce(state: S, action: A): S
}
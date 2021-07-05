package dashkudov.jule.mvi

import dashkudov.jule.model.JuleLogger

abstract class Reducer<S: State, A: Action>(protected val logger: JuleLogger, vararg _middlewares: Middleware<A>) {

    val middlewares = _middlewares

    abstract fun reduce(state: S, action: A): S
}
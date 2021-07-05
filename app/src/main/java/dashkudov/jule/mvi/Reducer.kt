package dashkudov.jule.mvi

import dashkudov.jule.model.JuleLogger

abstract class Reducer<S: State, A: Action>(vararg _middlewares: Middleware<A>) {

    abstract fun reduce(state: S, action: A): S?

    val middlewares = _middlewares

    val logger by lazy {
        JuleLogger().apply {
            connect(this@Reducer.javaClass)
        }
    }
}
package dashkudov.jule.mvi

import android.util.Log
import dashkudov.jule.model.JuleLogger
import dashkudov.jule.repository.ApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

class Store<A: Action, S: State> {
    var reducer: Reducer<S, A> by Delegates.notNull()
    var initialState: S by Delegates.notNull()
    private var state: S by Delegates.notNull()
    private var logger = JuleLogger()

    fun bind(flow: Flow<A>, render: (S) -> Unit) {
        with(reducer) {
            state = initialState
            render(state)
            MainScope().launch {
                middlewares.forEach { middleware ->
                    middleware.bind(flow).collect {
                        middleware.logger.log("MIDDLEWARE PRODUCE ACTION ${it.javaClass.simpleName}")
                        reduce(state, it)?.let {
                            state = it
                            render(state)
                        }
                    }
                }
            }
            MainScope().launch {
                flow.collect {
                    logger.log("REDUCER GET ACTION ${it.javaClass.simpleName}")
                    reduce(state, it)?.let {
                        state = it
                        render(state)
                    }
                }
            }
        }
    }
}
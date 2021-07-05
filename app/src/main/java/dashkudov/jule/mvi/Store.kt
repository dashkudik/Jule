package dashkudov.jule.mvi

import android.util.Log
import dashkudov.jule.repository.ApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

abstract class Store<A: Action, S: State> {
    var reducer: Reducer<S, A> by Delegates.notNull()
    var state: S by Delegates.notNull()

    fun bind(flow: Flow<A>, render: (S) -> Unit) {
        with(reducer) {
            MainScope().launch {
                middlewares.forEach {
                    it.bind(flow).collect {
                        val effect = reduce(state, it)
                        render(effect)
                    }
                }
            }
            MainScope().launch {
                flow.collect {
                    val effect = reduce(state, it)
                    render(effect)
                }
            }
        }
    }
}
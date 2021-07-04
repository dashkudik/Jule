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

abstract class Store<A: Action, S: State>(val apiRepository: ApiRepository) {
    var reducer: Reducer<S, A> by Delegates.notNull()
    var state: S by Delegates.notNull()

    fun bind(flow: Flow<A>, render: (S) -> Unit) {
        MainScope().launch {
            with(reducer) {
                middlewares.forEach {
                    it.bind(flow).collect {
                        val effect = reduce(state, it)
                        render(effect)
                    }
                }
                flow.collect {
                    val effect = reduce(state, it)
                    render(effect)
                }
            }
        }
    }
}
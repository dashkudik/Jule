package dashkudov.jule.mvi

import android.util.Log
import dashkudov.jule.model.JuleLogger
import dashkudov.jule.repository.ApiRepository
import dashkudov.jule.repository.PreferencesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

open class Store<A: Action, S: State> @Inject constructor(
    var apiRepository: ApiRepository,
    var preferencesRepository: PreferencesRepository
) {
    lateinit var middlewares: List<Middleware<A>>
    lateinit var reducer: Reducer<S, A>
}
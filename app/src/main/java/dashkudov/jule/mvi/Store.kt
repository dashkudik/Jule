package dashkudov.jule.mvi

import android.util.Log
import dashkudov.jule.model.JuleLogger
import dashkudov.jule.repository.ApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

class Store<A: Action, S: State> {
    var middlewareList: List<Middleware<A>> by Delegates.notNull()
}
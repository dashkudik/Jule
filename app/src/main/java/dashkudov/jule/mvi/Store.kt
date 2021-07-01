package dashkudov.jule.mvi

import dashkudov.jule.repository.ApiRepository
import javax.inject.Inject
import kotlin.properties.Delegates

abstract class Store<A: Action, S: State>(val apiRepository: ApiRepository) {
    open val reducer: Reducer<S, A> by Delegates.notNull()
    open val middlewares: List<Middleware<A>> by Delegates.notNull()
    open val initialState: S by Delegates.notNull()
}
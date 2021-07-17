package dashkudov.jule.mvi

import dashkudov.jule.model.JuleLogger
import dashkudov.jule.repository.ApiRepository
import dashkudov.jule.repository.PreferencesRepository
import javax.inject.Inject

open class Store<S: State, A: Action, N: News> @Inject constructor(
    val apiRepository: ApiRepository,
    val preferencesRepository: PreferencesRepository,
    val logger: JuleLogger
) {
    lateinit var middlewares: List<Middleware<A>>
    lateinit var reducer: Reducer<S, A, N>
}
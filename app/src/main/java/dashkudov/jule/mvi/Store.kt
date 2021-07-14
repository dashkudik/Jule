package dashkudov.jule.mvi

import dashkudov.jule.repository.ApiRepository
import dashkudov.jule.repository.PreferencesRepository
import javax.inject.Inject

open class Store<S: State, A: Action, N: News> @Inject constructor(
    var apiRepository: ApiRepository,
    var preferencesRepository: PreferencesRepository
) {
    lateinit var middlewares: List<Middleware<A>>
    lateinit var reducer: Reducer<S, A, N>
}
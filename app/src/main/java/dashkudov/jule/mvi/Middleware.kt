package dashkudov.jule.mvi

import dashkudov.jule.model.JuleLogger
import dashkudov.jule.repository.ApiRepository
import dashkudov.jule.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow

abstract class Middleware<A: Action>(store: Store<*, *, *>) {
    var apiRepository: ApiRepository = store.apiRepository
    var preferencesRepository: PreferencesRepository = store.preferencesRepository

    abstract suspend fun effect(action: A): A?
    suspend operator fun invoke(action: A) = effect(action)
}
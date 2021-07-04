package dashkudov.jule.mvi

import dashkudov.jule.repository.ApiRepository
import dashkudov.jule.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow

abstract class Middleware<A: Action> {
    abstract fun bind(actions: Flow<A>): Flow<A>

    lateinit var apiRepository: ApiRepository
    lateinit var preferencesRepository: PreferencesRepository
}
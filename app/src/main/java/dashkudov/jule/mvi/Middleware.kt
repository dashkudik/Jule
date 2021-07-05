package dashkudov.jule.mvi

import dashkudov.jule.model.JuleLogger
import dashkudov.jule.repository.ApiRepository
import dashkudov.jule.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow

abstract class Middleware<A: Action>(protected val logger: JuleLogger) {
    abstract fun bind(actions: Flow<A>): Flow<A>

    lateinit var apiRepository: ApiRepository
    lateinit var preferencesRepository: PreferencesRepository
}
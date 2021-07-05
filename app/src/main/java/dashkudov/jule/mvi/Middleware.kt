package dashkudov.jule.mvi

import dashkudov.jule.model.JuleLogger
import dashkudov.jule.repository.ApiRepository
import dashkudov.jule.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow

abstract class Middleware<A: Action> {
    abstract fun bind(actions: Flow<A>): Flow<A>

    val logger by lazy {
        JuleLogger().apply {
            connect(this@Middleware.javaClass)
        }
    }
    lateinit var apiRepository: ApiRepository
    lateinit var preferencesRepository: PreferencesRepository
}
package dashkudov.jule.presentation.start

import dashkudov.jule.mvi.Store
import dashkudov.jule.presentation.start.middlewares.ImplicitAuthMiddleware
import dashkudov.jule.repository.ApiRepository
import dashkudov.jule.repository.PreferencesRepository
import javax.inject.Inject

class StartStore @Inject constructor(
    apiRepository: ApiRepository,
    preferencesRepository: PreferencesRepository
): Store<StartState, StartAction, StartNews>(
    apiRepository, preferencesRepository
) {
    init {
        middlewares = listOf(ImplicitAuthMiddleware(this))
        reducer = StartReducer()
    }
}
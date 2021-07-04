package dashkudov.jule.di.module

import dagger.Module
import dagger.Provides
import dashkudov.jule.presentation.start.StartImplicitAuthMiddleware
import dashkudov.jule.presentation.start.StartReducer
import dashkudov.jule.presentation.start.StartState
import dashkudov.jule.presentation.start.StartStore
import dashkudov.jule.repository.ApiRepository
import dashkudov.jule.repository.PreferencesRepository

@Module(includes = [NetworkModule::class, PreferencesModule::class])
class StoreModule {

    @Provides
    fun provideStartStore(apiRepository: ApiRepository, preferencesRepository: PreferencesRepository): StartStore {
        return StartStore(apiRepository).apply {
            val startImplicitAuthMiddleware = StartImplicitAuthMiddleware().apply {
                this.apiRepository = apiRepository
                this.preferencesRepository = preferencesRepository
            }
            reducer = StartReducer(startImplicitAuthMiddleware)
            state = StartState.Shown
        }
    }
}
package dashkudov.jule.di.module

import dagger.Module
import dagger.Provides
import dashkudov.jule.mvi.Action
import dashkudov.jule.mvi.State
import dashkudov.jule.mvi.Store
import dashkudov.jule.repository.ApiRepository
import dashkudov.jule.repository.PreferencesRepository

@Module(includes = [PreferencesModule::class, NetworkModule::class])
class StoreModule {

    @Provides
    fun provideStore(
        apiRepository: ApiRepository,
        preferencesRepository: PreferencesRepository
    ): Store<Action, State> {
        return Store(apiRepository, preferencesRepository)
    }
}
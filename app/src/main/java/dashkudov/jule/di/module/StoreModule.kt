package dashkudov.jule.di.module

import dagger.Module
import dagger.Provides
import dashkudov.jule.model.JuleLogger
import dashkudov.jule.mvi.Action
import dashkudov.jule.mvi.News
import dashkudov.jule.mvi.State
import dashkudov.jule.mvi.Store
import dashkudov.jule.repository.ApiRepository
import dashkudov.jule.repository.PreferencesRepository

@Module(includes = [PreferencesModule::class, NetworkModule::class, LoggerModule::class])
class StoreModule {

    @Provides
    fun provideStore(
        apiRepository: ApiRepository,
        preferencesRepository: PreferencesRepository,
        logger: JuleLogger
    ): Store<State, Action, News> {
        return Store(apiRepository, preferencesRepository, logger)
    }
}
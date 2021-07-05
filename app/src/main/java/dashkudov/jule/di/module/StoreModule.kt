package dashkudov.jule.di.module

import dagger.Module
import dagger.Provides
import dashkudov.jule.model.JuleLogger
import dashkudov.jule.presentation.start.StartImplicitAuthMiddleware
import dashkudov.jule.presentation.start.StartReducer
import dashkudov.jule.presentation.start.StartState
import dashkudov.jule.presentation.start.StartStore
import dashkudov.jule.repository.ApiRepository
import dashkudov.jule.repository.PreferencesRepository

@Module(includes = [NetworkModule::class, PreferencesModule::class, LoggerModule::class])
class StoreModule {

    @Provides
    fun provideStartStore(
            apiRepository: ApiRepository,
            preferencesRepository: PreferencesRepository,
            middleWareLogger: JuleLogger,
            reducerLogger: JuleLogger
    ): StartStore {
        return StartStore().apply {
            val startImplicitAuthMiddleware = StartImplicitAuthMiddleware(middleWareLogger).apply {
                this.apiRepository = apiRepository
                this.preferencesRepository = preferencesRepository
            }
            reducer = StartReducer(reducerLogger, startImplicitAuthMiddleware)
            state = StartState.Shown
        }
    }
}
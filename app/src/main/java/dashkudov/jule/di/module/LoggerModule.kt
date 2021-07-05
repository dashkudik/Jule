package dashkudov.jule.di.module

import dagger.Module
import dagger.Provides
import dashkudov.jule.model.JuleLogger
import javax.inject.Singleton

@Module
class LoggerModule {
    @Provides
    fun provideLogger() = JuleLogger()
}

package dashkudov.jule.di.module

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelBuilder::class])
class ContextModule {
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }
}
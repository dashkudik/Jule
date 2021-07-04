package dashkudov.jule.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dashkudov.jule.Config
import dashkudov.jule.repository.PreferencesRepository
import dashkudov.jule.repository.PreferencesRepositoryImpl

@Module(includes = [ContextModule::class])
class PreferencesModule {

    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences(Config.KEY, Context.MODE_PRIVATE)
    }

    @Provides
    fun providePreferences(preferences: SharedPreferences): PreferencesRepository {
        return PreferencesRepositoryImpl(preferences)
    }
}
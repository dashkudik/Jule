package dashkudov.jule.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dashkudov.jule.repository.PreferencesRepository
import dashkudov.jule.repository.PreferencesRepositoryImpl
import javax.inject.Singleton

/*
@Module
class PreferenceModule {

    @Provides
    @Singleton
    fun providePreferenceRepository(sharedPreferences: SharedPreferences): PreferencesRepository =
        PreferencesRepositoryImpl(sharedPreferences)

    @Provides
    @Singleton
    fun providePreferenceManager(application: Application): SharedPreferences =
        application.getSharedPreferences(DataConfig.PREF_NAME, Context.MODE_PRIVATE)

} */
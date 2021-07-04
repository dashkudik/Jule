package dashkudov.jule.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dashkudov.jule.presentation.intro.ui.StartFragment

@Module
abstract class MainActivityProviders {

    @ContributesAndroidInjector
    abstract fun introFragment(): StartFragment

}
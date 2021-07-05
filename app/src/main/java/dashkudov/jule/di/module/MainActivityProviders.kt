package dashkudov.jule.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dashkudov.jule.presentation.auth.ui.AuthFragment
import dashkudov.jule.presentation.start.ui.StartFragment

@Module
abstract class MainActivityProviders {

    @ContributesAndroidInjector
    abstract fun startFragment(): StartFragment

    @ContributesAndroidInjector
    abstract fun authFragment(): AuthFragment

}
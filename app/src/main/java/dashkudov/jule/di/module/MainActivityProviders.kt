package dashkudov.jule.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dashkudov.jule.presentation.auth.ui.AuthFragment
import dashkudov.jule.presentation.feed.ui.FeedFragment
import dashkudov.jule.presentation.sign_up.ui.SignUpFragment
import dashkudov.jule.presentation.start.ui.StartFragment

@Module
abstract class MainActivityProviders {

    @ContributesAndroidInjector
    abstract fun startFragment(): StartFragment

    @ContributesAndroidInjector
    abstract fun feedFragment(): FeedFragment

    @ContributesAndroidInjector
    abstract fun authFragment(): AuthFragment

    @ContributesAndroidInjector
    abstract fun signUpFragment(): SignUpFragment

}
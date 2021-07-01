package dashkudov.jule.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dashkudov.jule.presentation.intro.ui.IntroFragment

@Module
abstract class MainActivityFragments {

    @ContributesAndroidInjector
    abstract fun introFragment(introFragment: IntroFragment): IntroFragment

}
package dashkudov.jule.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dashkudov.jule.presentation.MainActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity
}
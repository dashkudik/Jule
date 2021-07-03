package dashkudov.jule.di.module

import androidx.lifecycle.ViewModel
import dashkudov.jule.di.qualifier.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dashkudov.jule.presentation.intro.ui.IntroViewModel

@Module
abstract class AppViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(IntroViewModel::class)
    abstract fun introViewModel(introViewModel: IntroViewModel): ViewModel
}
package dashkudov.jule.di.module

import androidx.lifecycle.ViewModel
import dashkudov.jule.di.qualifier.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dashkudov.jule.presentation.auth.ui.FeedViewModel
import dashkudov.jule.presentation.start.ui.StartViewModel

@Module
abstract class AppViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(StartViewModel::class)
    abstract fun startViewModel(authViewModel: StartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FeedViewModel::class)
    abstract fun authViewModel(feedViewModel: FeedViewModel): ViewModel
}
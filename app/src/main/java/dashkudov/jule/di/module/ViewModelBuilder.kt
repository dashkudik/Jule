package dashkudov.jule.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dashkudov.jule.di.ViewModelFactory

@Module(
    includes = [
        AppViewModelBuilder::class,
        StoreModule::class
    ]
)
abstract class ViewModelBuilder {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
package dashkudov.jule.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import dashkudov.jule.presentation.Store
import dashkudov.jule.presentation.auth.AuthStore

@Module(includes = [NetworkModule::class])
class StoreModule {


}

package dashkudov.jule.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dashkudov.jule.JuleApp
import dashkudov.jule.di.module.ActivityBuilder
import dashkudov.jule.di.module.MainActivityFragments
import dashkudov.jule.di.module.NetworkModule
import dashkudov.jule.di.module.ViewModelBuilder
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        ActivityBuilder::class,
        ViewModelBuilder::class,
    ]
)
interface JuleComponent : AndroidInjector<JuleApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): JuleComponent
    }

}
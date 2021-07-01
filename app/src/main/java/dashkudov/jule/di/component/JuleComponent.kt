package dashkudov.jule.di.component

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dashkudov.jule.JuleApp
import dashkudov.jule.dataSources.ApiDataSource
import dashkudov.jule.di.module.NetworkModule
import dashkudov.jule.presentation.MainActivity
import dashkudov.jule.repository.ApiRepository
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class
    ]
)
interface JuleComponent {
    fun inject(mainActivity: MainActivity)
    val apiRepository: ApiRepository
}
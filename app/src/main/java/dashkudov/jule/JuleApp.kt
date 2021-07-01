package dashkudov.jule

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dashkudov.jule.di.component.DaggerJuleComponent

class JuleApp: DaggerApplication() {

    init {
        DaggerJuleComponent.builder().application(this).build().inject(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerJuleComponent.builder().build()

    override fun onCreate() {
        super.onCreate()


    }

}
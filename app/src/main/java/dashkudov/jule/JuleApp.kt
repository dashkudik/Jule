package dashkudov.jule

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dashkudov.jule.di.component.DaggerJuleComponent

class JuleApp: DaggerApplication() {

    init {
        DaggerJuleComponent.builder().application(this).build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerJuleComponent
        .builder()
        .application(this)
        .build()

    override fun onCreate() {
        super.onCreate()


    }

}
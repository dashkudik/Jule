package dashkudov.jule.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.android.support.DaggerAppCompatActivity
import dashkudov.jule.R
import dashkudov.jule.api.request.auth.AuthRequest
import dashkudov.jule.di.component.DaggerJuleComponent
import dashkudov.jule.presentation.auth.AuthStore
import dashkudov.jule.repository.ApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject lateinit var authStore: AuthStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("TEST1", authStore.repository.javaClass.name.toString())

    }

}
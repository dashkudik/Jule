package dashkudov.jule.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dashkudov.jule.R
import dashkudov.jule.api.request.auth.AuthRequest
import dashkudov.jule.di.component.DaggerJuleComponent
import dashkudov.jule.repository.ApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var apiRepository: ApiRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val juleComponent = DaggerJuleComponent.create()
        juleComponent.inject(this)

        val authRequest = AuthRequest(
            login = "dimitri",
            password = "123"
        )

        CoroutineScope(Dispatchers.Default).launch {
            apiRepository.login(authRequest)
        }
    }
}
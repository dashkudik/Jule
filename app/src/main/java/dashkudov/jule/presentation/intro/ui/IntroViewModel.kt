package dashkudov.jule.presentation.intro.ui

import androidx.lifecycle.ViewModel
import dashkudov.jule.api.request.auth.AuthRequest
import dashkudov.jule.presentation.intro.IntroStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class IntroViewModel @Inject constructor(): ViewModel() {

    fun bind() {
        CoroutineScope(Dispatchers.Default).launch {
            //introStore.apiRepository.login(AuthRequest("23", "243"))
        }
    }
}
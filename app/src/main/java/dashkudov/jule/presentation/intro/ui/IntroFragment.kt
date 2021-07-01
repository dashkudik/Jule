package dashkudov.jule.presentation.intro.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dashkudov.jule.R
import dashkudov.jule.api.request.auth.AuthRequest
import dashkudov.jule.presentation.BaseFragment
import dashkudov.jule.presentation.intro.IntroStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class IntroFragment: BaseFragment(R.layout.f_intro) {

    val introViewModel: IntroViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind()

    }



    @Inject
    lateinit var introStore: IntroStore

    fun bind() {
        CoroutineScope(Dispatchers.Default).launch {
            introStore.apiRepository.login(AuthRequest("23", "243"))
        }
    }

}
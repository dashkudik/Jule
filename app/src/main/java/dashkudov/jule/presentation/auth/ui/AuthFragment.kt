package dashkudov.jule.presentation.auth.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import dashkudov.jule.R
import dashkudov.jule.api.request.auth.AuthRequest
import dashkudov.jule.common.Extensions.click
import dashkudov.jule.common.Extensions.launchWhenStarted
import dashkudov.jule.common.Extensions.navigate
import dashkudov.jule.common.Extensions.string
import dashkudov.jule.model.JuleLogger
import dashkudov.jule.mvi.MviView
import dashkudov.jule.presentation.BaseFragment
import dashkudov.jule.presentation.auth.AuthAction
import dashkudov.jule.presentation.auth.AuthNews
import dashkudov.jule.presentation.auth.AuthState
import dashkudov.jule.presentation.start.StartNews
import dashkudov.jule.presentation.start.StartState
import dashkudov.jule.presentation.start.ui.StartFragment
import kotlinx.android.synthetic.main.f_auth.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthFragment: BaseFragment(R.layout.f_auth), MviView<AuthState, AuthNews> {

    @Inject
    lateinit var logger: JuleLogger

    val authViewModel by lazy {
        viewModelFactory.create(AuthViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logger.connect(javaClass)

        with(authViewModel) {
            bind(lifecycleScope, this@AuthFragment)
        }

        btnAuth.click {
            val authRequest = AuthRequest(
                login = input_login.string(),
                password = input_password.string()
            )
            val action = AuthAction.Auth(authRequest)
            MainScope().launch {
                authViewModel.obtainAction(action)
            }
        }
    }

    override fun renderState(state: AuthState) {
        when (state) {
            is AuthState.Default -> {
                lifecycleScope.launch {
                    state.navDirections?.let {
                        navigate(it)
                    }
                }
            }
        }
    }

    override fun renderNews(new: AuthNews) {
        when (new) {
            is AuthNews.Message -> {
                Toast.makeText(requireActivity(), new.content, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val TAG = "StartFragment"
        const val TAG_FLOW = "UiFlow"
    }
}
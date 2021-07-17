package dashkudov.jule.presentation.sign_up.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import dashkudov.jule.R
import dashkudov.jule.api.model.GenderApi
import dashkudov.jule.api.request.auth.AuthRequest
import dashkudov.jule.api.request.sign_up.SignUpRequest
import dashkudov.jule.common.Extensions.click
import dashkudov.jule.common.Extensions.navigate
import dashkudov.jule.common.Extensions.string
import dashkudov.jule.common.ViewExtensions.gone
import dashkudov.jule.common.ViewExtensions.visible
import dashkudov.jule.model.JuleLogger
import dashkudov.jule.mvi.MviView
import dashkudov.jule.presentation.BaseFragment
import dashkudov.jule.presentation.auth.AuthAction
import dashkudov.jule.presentation.auth.AuthNews
import dashkudov.jule.presentation.auth.AuthState
import dashkudov.jule.presentation.sign_up.SignUpAction
import dashkudov.jule.presentation.sign_up.SignUpNews
import dashkudov.jule.presentation.sign_up.SignUpState
import kotlinx.android.synthetic.main.f_auth.*
import kotlinx.android.synthetic.main.f_auth.btnSignUp
import kotlinx.android.synthetic.main.f_sign_up.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpFragment: BaseFragment(R.layout.f_sign_up), MviView<SignUpState, SignUpNews> {

    @Inject
    lateinit var logger: JuleLogger

    val signUpViewModel by lazy {
        viewModelFactory.create(SignUpViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logger.connect(javaClass)

        with(signUpViewModel) {
            bind(viewLifecycleOwner.lifecycleScope, this@SignUpFragment)
        }

        btnSignUp.click {
            val signUpRequest = SignUpRequest(
                login = "",
                password = "1212",
                email = "d@ayadddddsdcahoo.com",
                name = "Димы",
                gender = GenderApi.M
            )
            viewLifecycleOwner.lifecycleScope.launch {
                signUpViewModel.obtainAction(
                    SignUpAction.SignUp(signUpRequest)
                )
            }
        }
    }

    override fun renderState(state: SignUpState) {
        when (state) {
            is SignUpState.Default -> {
                state.navDirections?.let {
                    navigate(it)
                }
            }
        }
    }

    override fun renderNews(new: SignUpNews) {
        when (new) {
            is SignUpNews.Message -> {
                Toast.makeText(requireContext(), new.content, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
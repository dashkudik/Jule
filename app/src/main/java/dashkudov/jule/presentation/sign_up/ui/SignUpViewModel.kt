package dashkudov.jule.presentation.sign_up.ui

import dashkudov.jule.presentation.BaseViewModel
import dashkudov.jule.presentation.auth.*
import dashkudov.jule.presentation.sign_up.SignUpAction
import dashkudov.jule.presentation.sign_up.SignUpNews
import dashkudov.jule.presentation.sign_up.SignUpState
import dashkudov.jule.presentation.sign_up.SignUpStore
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class SignUpViewModel @Inject constructor(): BaseViewModel<SignUpState, SignUpAction, SignUpNews>() {

    override val stateFlow = MutableStateFlow<SignUpState>(SignUpState.Default())
    override val actionFlow = MutableSharedFlow<SignUpAction?>()
    override val newsFlow = MutableSharedFlow<SignUpNews>()

    @Inject override lateinit var store: SignUpStore
}
package dashkudov.jule.presentation.auth.ui

import dashkudov.jule.presentation.BaseViewModel
import dashkudov.jule.presentation.auth.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class AuthViewModel @Inject constructor(): BaseViewModel<AuthState, AuthAction, AuthNews>() {

    override val stateFlow = MutableStateFlow<AuthState>(AuthState.Default())
    override val actionFlow = MutableSharedFlow<AuthAction?>()
    override val newsFlow = MutableSharedFlow<AuthNews>()

    @Inject override lateinit var store: AuthStore
}
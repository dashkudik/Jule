package dashkudov.jule.presentation.start.ui

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dashkudov.jule.model.JuleLogger
import dashkudov.jule.mvi.Middleware
import dashkudov.jule.mvi.Store
import dashkudov.jule.presentation.BaseViewModel
import dashkudov.jule.presentation.start.*
import dashkudov.jule.repository.ApiRepository
import dashkudov.jule.repository.PreferencesRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class StartViewModel @Inject constructor(): BaseViewModel<StartState, StartAction>() {

    override val stateFlow = MutableStateFlow<StartState>(StartState.LogoShown)
    override val actionFlow = MutableStateFlow<StartAction>(StartAction.ImplicitAuth)

    @Inject override lateinit var store: StartStore
}
package dashkudov.jule.presentation.start.ui

import androidx.lifecycle.viewModelScope
import dashkudov.jule.presentation.BaseViewModel
import dashkudov.jule.presentation.start.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartViewModel @Inject constructor(): BaseViewModel<StartState, StartAction, StartNews>() {

    override val stateFlow = MutableStateFlow<StartState>(StartState.Default())
    override val newsFlow = MutableSharedFlow<StartNews>()
    override val actionFlow = MutableSharedFlow<StartAction?>()

    @Inject override lateinit var store: StartStore
}
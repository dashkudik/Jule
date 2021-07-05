package dashkudov.jule.presentation.auth.ui

import androidx.lifecycle.ViewModel
import dashkudov.jule.presentation.start.StartAction
import dashkudov.jule.presentation.start.StartState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthViewModel @Inject constructor(): ViewModel() {

    @Inject lateinit var startStore: StartStore

    fun bind(flow: Flow<StartAction>, render: (StartState) -> Unit) {
        startStore.bind(flow, render)
    }
}
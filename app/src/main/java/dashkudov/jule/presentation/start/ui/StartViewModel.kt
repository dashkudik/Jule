package dashkudov.jule.presentation.start.ui

import androidx.lifecycle.ViewModel
import dashkudov.jule.presentation.start.StartAction
import dashkudov.jule.presentation.start.StartState
import dashkudov.jule.presentation.start.StartStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StartViewModel @Inject constructor(): ViewModel() {

    @Inject lateinit var startStore: StartStore

    fun bind(flow: Flow<StartAction>, render: (StartState) -> Unit) {
        startStore.bind(flow, render)
    }
}
package dashkudov.jule.presentation.intro.ui

import androidx.lifecycle.ViewModel
import dashkudov.jule.presentation.intro.StartAction
import dashkudov.jule.presentation.intro.StartState
import dashkudov.jule.presentation.intro.StartStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartViewModel @Inject constructor(): ViewModel() {

    @Inject lateinit var startStore: StartStore

    fun bind(flow: Flow<StartAction>, render: (StartState) -> Unit) {
        startStore.bind(flow, render)
    }
}
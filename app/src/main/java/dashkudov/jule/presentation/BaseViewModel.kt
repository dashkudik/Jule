package dashkudov.jule.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dashkudov.jule.model.JuleLogger
import dashkudov.jule.mvi.Action
import dashkudov.jule.mvi.State
import dashkudov.jule.mvi.Store
import dashkudov.jule.presentation.start.StartStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel<S: State, A: Action>: ViewModel() {

    @Inject
    lateinit var logger: JuleLogger

    abstract val stateFlow: MutableStateFlow<S>
    abstract val actionFlow: MutableStateFlow<A>
    abstract val store: Store<A, S>

    fun bind() {
        viewModelScope.launch {
            actionFlow.collect {
                logger.log("AF collects ${it.javaClass.simpleName}")
                CoroutineScope(Dispatchers.Default).launch {
                    store.middlewares.forEach { middleware ->
                        middleware(it)?.let {
                            logger.log("${middleware.javaClass.simpleName} effects ${it.javaClass.simpleName}")
                            actionFlow.value = it
                        }
                    }
                }
                store.reducer(stateFlow.value, it)?.let {
                    stateFlow.value = it
                }
            }
        }
    }
}
package dashkudov.jule.presentation.start.ui

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dashkudov.jule.model.JuleLogger
import dashkudov.jule.mvi.Middleware
import dashkudov.jule.mvi.Store
import dashkudov.jule.presentation.start.*
import dashkudov.jule.repository.ApiRepository
import dashkudov.jule.repository.PreferencesRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class StartViewModel @Inject constructor(): ViewModel() {

    val startStateFlow = MutableStateFlow<StartState>(StartState.LogoShown)
    val startActionFlow = MutableStateFlow<StartAction>(StartAction.ImplicitAuth)

    @Inject
    lateinit var logger: JuleLogger

    fun on() {
        viewModelScope.launch {
            startActionFlow.collect {
                logger.log("AF collects ${it.javaClass.simpleName}")
                CoroutineScope(Dispatchers.Default).launch {
                    startStore.middlewares.forEach { middleware ->
                        middleware(it)?.let {
                            logger.log("${middleware.javaClass.simpleName} effects ${it.javaClass.simpleName}")
                            startActionFlow.value = it
                        }
                    }
                }
                startStore.reducer(startStateFlow.value, it)?.let {
                    startStateFlow.emit(it)
                }
            }
        }
    }

    @Inject lateinit var startStore: StartStore
}
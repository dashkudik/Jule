package dashkudov.jule.presentation

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import dashkudov.jule.common.Extensions.launchWhenStarted
import dashkudov.jule.model.JuleLogger
import dashkudov.jule.mvi.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel<S: State, A: Action, N: News>: ViewModel() {

    @Inject
    lateinit var logger: JuleLogger

    abstract val stateFlow: MutableStateFlow<S>
    abstract val newsFlow: MutableSharedFlow<N>
    abstract val actionFlow: MutableSharedFlow<A?>
    abstract val store: Store<S, A, N>

    suspend fun obtainAction(action: A) {
        actionFlow.emit(action)
    }

    fun bind(lifecycleScope: LifecycleCoroutineScope, mviView: MviView<S, N>) {
        logger.connect(javaClass)

        stateFlow
            .onEach(mviView::renderState)
            .launchWhenStarted(lifecycleScope)

        newsFlow
            .onEach(mviView::renderNews)
            .launchWhenStarted(lifecycleScope)

        viewModelScope.launch {
            actionFlow.collect {
                it?.let {
                    logger.log("AF collects ${it.javaClass.simpleName}")
                    CoroutineScope(Dispatchers.Default).launch {
                        store.middlewares.forEach { middleware ->
                            middleware(it)?.let {
                                logger.log("${middleware.javaClass.simpleName} effects $it")
                                actionFlow.emit(it)
                            }
                        }
                    }


                    val reduced = store.reducer(stateFlow.value, it)

                    logger.log("Reduced state - ${reduced.first}")

                    reduced.first?.let {
                        stateFlow.value = it
                    }

                    reduced.second?.let {
                        newsFlow.emit(it)
                    }
                }
            }
        }
    }
}
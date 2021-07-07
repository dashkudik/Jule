package dashkudov.jule.presentation.start.ui

import androidx.lifecycle.ViewModel
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

    val startStateFlow by lazy {
        MutableStateFlow<StartState>(StartState.LogoAnimating)
    }

    @Inject
    lateinit var apiRepository: ApiRepository
    @Inject
    lateinit var preferencesRepository: PreferencesRepository

    @Inject
    lateinit var logger: JuleLogger

    private val startStore: Store<StartAction, StartState> by lazy {
        Store<StartAction, StartState>().apply {
            middlewareList = listOf(
                    ImplicitAuthMiddleware().apply {
                        this.apiRepository = this@StartViewModel.apiRepository
                        this.preferencesRepository = this@StartViewModel.preferencesRepository
                    }, LogoAnimationSuspenseMiddleware().apply {
                this.apiRepository = this@StartViewModel.apiRepository
                this.preferencesRepository = this@StartViewModel.preferencesRepository
            })
        }
    }

    suspend fun on(uiFlow: Flow<StartAction>) {
        uiFlow.collect {
            reduce(it, startStore.middlewareList)
        }
    }

    fun reduce(action: StartAction?, middlewares: List<Middleware<StartAction>>) {
        MainScope().launch {
            CoroutineScope(Dispatchers.Default).launch {
                middlewares.forEach { m ->
                    action?.let {
                        reduce(m.effect(action), middlewares)
                    }
                }
            }
            action?.let {
                logger.log(action.javaClass.simpleName)
                when (it) {
                    is StartAction.LogoAnimationSuspenseRequired -> {
                        startStateFlow.value = StartState.LogoAnimating
                    }
                    is StartAction.ImplicitAuthDone -> {
                        startStateFlow.value = StartState.ToAuth(it.interpretedError?.userFriendlyInterpretation)
                    }
                    is StartAction.ImplicitAuthImpossible -> {
                        startStateFlow.value = StartState.ToAuth()
                    }
                }
            }
        }
    }
}
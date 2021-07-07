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
        MutableStateFlow<StartState>(StartState.LogoShown)
    }

    val startActionFlow by lazy {
        MutableStateFlow<StartAction>(StartAction.ImplicitAuth)
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

    suspend fun on() {
        logger.connect(this.javaClass)
        startActionFlow.collect { action ->
            logger.log("AF collects ${action.javaClass.simpleName}")
            CoroutineScope(Dispatchers.Default).launch {
                startStore.middlewareList.forEach { m ->
                    m.effect(action)?.let {
                        logger.log("${m.javaClass.simpleName} effects ${it.javaClass.simpleName}")
                        startActionFlow.value = it
                    }
                }
            }
            reduce(action)
        }
    }


    fun reduce(action: StartAction) {
         logger.log("Reduce action ${action.javaClass.simpleName}")
         when (action) {

             is StartAction.ImplicitAuthDone -> {
                 with(action.interpretedError?.userFriendlyInterpretation) {
                     if (this != null) {
                         startStateFlow.value = StartState.ToAuth(this)
                     } else  {
                         startStateFlow.value = StartState.ToFeed
                     }
                 }
             }

             is StartAction.ImplicitAuthImpossible -> {
                 startStateFlow.value = StartState.ToAuth()
             }
         }
    }
}
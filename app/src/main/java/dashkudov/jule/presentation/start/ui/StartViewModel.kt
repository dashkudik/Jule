package dashkudov.jule.presentation.start.ui

import androidx.lifecycle.ViewModel
import dashkudov.jule.mvi.Store
import dashkudov.jule.presentation.start.StartAction
import dashkudov.jule.presentation.start.ImplicitAuthMiddleware
import dashkudov.jule.presentation.start.StartReducer
import dashkudov.jule.presentation.start.StartState
import dashkudov.jule.repository.ApiRepository
import dashkudov.jule.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StartViewModel @Inject constructor(): ViewModel() {

    @Inject lateinit var apiRepository: ApiRepository
    @Inject lateinit var preferencesRepository: PreferencesRepository

    private val startStore: Store<StartAction, StartState> by lazy {
        Store<StartAction, StartState>().apply {
            reducer = StartReducer(ImplicitAuthMiddleware().apply {
                this.apiRepository = this@StartViewModel.apiRepository
                this.preferencesRepository = this@StartViewModel.preferencesRepository
            })
            initialState = StartState.LogoShown
        }
    }

    fun bind(flow: Flow<StartAction>, render: (StartState) -> Unit) {
        startStore.bind(flow, render)
    }
}
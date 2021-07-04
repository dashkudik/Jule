package dashkudov.jule.presentation.intro

import dashkudov.jule.mvi.Store
import dashkudov.jule.repository.ApiRepository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

class StartStore @Inject constructor(apiRepository: ApiRepository) : Store<StartAction, StartState>(apiRepository) {
    override fun bind(flow: Flow<StartAction>, render: (StartState) -> Unit) {
        MainScope().launch {
            with(reducer) {
                middlewares.forEach {
                    it.bind(flow).collect {
                        val effect = reduce(state, it)
                        render(effect)
                    }
                }
                flow.collect {
                    val effect = reduce(state, it)
                    render(effect)
                }
            }
        }
    }
}
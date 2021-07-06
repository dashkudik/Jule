package dashkudov.jule.presentation.start

import dashkudov.jule.common.MapErrorUtil.doRequest
import dashkudov.jule.common.MapErrorUtil.extractLocalError
import dashkudov.jule.mvi.Middleware
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ImplicitAuthMiddleware: Middleware<StartAction>() {

    override fun bind(actions: Flow<StartAction>): Flow<StartAction> = flow {
        actions.collect {
            when (it) {
                is StartAction.ImplicitAuth -> {
                    val lastKnownAuthRequest = preferencesRepository.getAuthRequest()
                    if (lastKnownAuthRequest != null) {
                        doRequest(
                            responseAsync = {
                                apiRepository.auth(lastKnownAuthRequest)
                            },
                            onOk = {
                                emit(StartAction.ImplicitAuthDone())
                            },
                            onApiErrorStatus = {
                                emit(StartAction.ImplicitAuthDone(interpretedError = this))
                            },
                            onException = {
                                emit(StartAction.ImplicitAuthDone(interpretedError = extractLocalError()))
                            },
                        )
                    } else {
                        emit(StartAction.ImplicitAuthImpossible)
                    }
                }
            }
        }
    }.flowOn(Dispatchers.Default)
}
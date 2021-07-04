package dashkudov.jule.presentation.start

import android.util.Log
import dashkudov.jule.MapErrorUtil.doRequest
import dashkudov.jule.MapErrorUtil.extractError
import dashkudov.jule.mvi.Middleware
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class StartImplicitAuthMiddleware: Middleware<StartAction>() {

    override fun bind(actions: Flow<StartAction>): Flow<StartAction> = flow {
        actions.collect {
            Log.d("TEST", "Middleware received ${it.javaClass.simpleName} ")
            when (it) {
                is StartAction.ImplicitAuth -> {
                    val lastKnownAuthRequest = preferencesRepository.getAuthRequest()
                    if (lastKnownAuthRequest != null) {
                        doRequest(
                            responseAsync = {
                                delay(2000)
                                apiRepository.auth(lastKnownAuthRequest)
                            },
                            onOk = {
                                emit(StartAction.ImplicitAuthDone())
                            },
                            onErrorStatus = {
                                emit(StartAction.ImplicitAuthDone(extractError()))
                            },
                            onException = {
                                emit(StartAction.ImplicitAuthDone(extractError()))
                            }
                        )
                    } else {
                        emit(StartAction.ImplicitAuthImpossible)
                    }
                }
            }
        }
    }.flowOn(Dispatchers.Default)
}
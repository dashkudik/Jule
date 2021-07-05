package dashkudov.jule.presentation.start

import android.util.Log
import dashkudov.jule.MapErrorUtil.doRequest
import dashkudov.jule.MapErrorUtil.extractError
import dashkudov.jule.model.JuleLogger
import dashkudov.jule.mvi.Middleware
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StartImplicitAuthMiddleware @Inject constructor(logger: JuleLogger): Middleware<StartAction>(logger) {

    override fun bind(actions: Flow<StartAction>): Flow<StartAction> = flow {
        logger.connect(this@StartImplicitAuthMiddleware.javaClass)
        actions.collect {
            when (it) {
                is StartAction.ImplicitAuth -> {
                    val lastKnownAuthRequest = preferencesRepository.getAuthRequest()
                    if (lastKnownAuthRequest != null) {
                        doRequest(
                            responseAsync = {
                                logger.log("Send request")
                                delay(2000)
                                apiRepository.auth(lastKnownAuthRequest)
                            },
                            onOk = {
                                logger.log("ImplicitAuthDone OK")
                                emit(StartAction.ImplicitAuthDone())
                            },
                            onErrorStatus = {
                                logger.log("ImplicitAuthDone ERROR")
                                emit(StartAction.ImplicitAuthDone(extractError()))
                            },
                            onException = {
                                logger.log("ImplicitAuthDone EXCEPTION")
                                emit(StartAction.ImplicitAuthDone(extractError()))
                            }
                        )
                    } else {
                        logger.log("ImplicitAuthImpossible")
                        emit(StartAction.ImplicitAuthImpossible)
                    }
                }
            }
        }
    }.flowOn(Dispatchers.Default)
}
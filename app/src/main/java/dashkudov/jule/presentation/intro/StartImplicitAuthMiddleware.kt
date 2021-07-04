package dashkudov.jule.presentation.intro

import android.util.Log
import com.google.gson.Gson
import dashkudov.jule.api.ApiResponse
import dashkudov.jule.mvi.Middleware
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import kotlin.properties.Delegates

class StartImplicitAuthMiddleware: Middleware<StartAction>() {

    override fun bind(actions: Flow<StartAction>): Flow<StartAction> = flow {
        actions.collect {
            when (it) {
                is StartAction.ImplicitAuth -> {
                    val lastKnownAuthRequest = preferencesRepository.getAuthRequest()
                    if (lastKnownAuthRequest != null) {
                        var response: ApiResponse<*>? = null
                        try {
                            response = apiRepository.auth(lastKnownAuthRequest)
                        } catch (e: HttpException) {
                            val responseString = e.response()?.errorBody()?.string()
                            response = Gson().fromJson(responseString, ApiResponse::class.java)
                            emit(StartAction.OnError(response?.message ?: "null"))
                        } finally {
                            Log.i("TEST1", response?.message ?: "null")
                        }
                        /*(if (authResponse.ok) {
                            emit(StartAction.OnFeed)
                        } else {
                            emit(StartAction.OnAuth)
                        }*/
                    } else {
                        emit(StartAction.OnAuth)
                    }
                }
            }
        }
    }.flowOn(Dispatchers.Default)
}
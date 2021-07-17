package dashkudov.jule.mvi

import dashkudov.jule.api.ApiResponse
import dashkudov.jule.api.ResponseStatus
import dashkudov.jule.common.MapErrorUtil.extractApiError
import dashkudov.jule.model.ApiErrorModel
import dashkudov.jule.model.JuleLogger
import dashkudov.jule.repository.ApiRepository
import dashkudov.jule.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.lang.Exception

abstract class Middleware<A>(store: Store<*, *, *>) {
    var apiRepository: ApiRepository = store.apiRepository
    var preferencesRepository: PreferencesRepository = store.preferencesRepository
    var logger: JuleLogger = store.logger.apply { connect(javaClass) }

    abstract suspend fun effect(action: A): A?
    suspend operator fun invoke(action: A) = effect(action)

    suspend fun <T> doRequest(
        responseAsync: suspend () -> ApiResponse<T>,
        onOk: T.() -> Unit,
        onApiErrorStatus: ApiErrorModel.() -> Unit,
        onException: Exception.() -> Unit,
    ) {
        try {
            val response = responseAsync()
            if (response.ok) {
                response.data!!.onOk()
            } else {
                ApiErrorModel(response.message, response.status).onApiErrorStatus()
            }
        } catch (e: HttpException) {
            val error = e.extractApiError()
            error.onApiErrorStatus()
        } catch (e: Exception) {
            e.onException()
        }
    }
}
  
package dashkudov.jule.common

import com.google.gson.Gson
import dashkudov.jule.api.ApiResponse
import dashkudov.jule.api.ResponseStatus
import dashkudov.jule.api.request.auth.AuthResponseStatus
import dashkudov.jule.model.ErrorModel
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

object MapErrorUtil {

    fun HttpException.extractError(): ErrorModel {
        val responseString = response()?.errorBody()?.string()
        val response = Gson().fromJson(responseString, ApiResponse::class.java)
        return ErrorModel(
            message = response.message?.toUserFriendly(response.status)
        )
    }

    fun Exception.extractError(): ErrorModel {
        return ErrorModel(
            message = "Говно инет"
        )
    }

    fun ApiResponse<*>.extractError() = ErrorModel(message?.toUserFriendly(status))

    fun String.toUserFriendly(status: ResponseStatus): String? {
        return when (status) {
            ResponseStatus.ERROR_UNAUTHORIZED -> {
                when (this) {
                    AuthResponseStatus.BAD_CREDENTIALS.name -> {
                        "Неправильный логин или пароль"
                    }
                    else -> null
                }
            }
            else -> null
        }
    }

    suspend fun <T> doRequest(
        responseAsync: suspend () -> ApiResponse<T>,
        onOk: suspend T?.() -> Unit,
        onErrorStatus: suspend ApiResponse<T>.() -> Unit,
        onException: suspend Exception.() -> Unit
    ) {
        try {
            val response = responseAsync()
            if (response.ok) {
                response.data?.onOk()
            } else {
                response.onErrorStatus()
            }
        } catch (e: HttpException) {
            e.onException()
        } catch (e: IOException) {
            e.onException()
        }
    }
}
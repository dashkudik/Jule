  
package dashkudov.jule.common

import com.google.gson.Gson
import dashkudov.jule.api.ApiResponse
import dashkudov.jule.api.ResponseStatus
import dashkudov.jule.api.request.auth.AuthResponseStatus
import dashkudov.jule.model.ApiErrorModel
import dashkudov.jule.model.LocalError
import dashkudov.jule.model.LocalErrorModel
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception



object MapErrorUtil {

    fun HttpException.extractApiError(): ApiErrorModel {
        val responseString = response()?.errorBody()?.string()
        val response = Gson().fromJson(responseString, ApiResponse::class.java)
        return ApiErrorModel(
            message = response.message,
            status = response.status
        )
    }

    fun Exception.extractLocalError(): LocalErrorModel {
        return LocalErrorModel(
            if (this is IOException) {
                LocalError.IO
            } else {
                LocalError.NE_ZNAI
            }
        )
    }
}
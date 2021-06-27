package dashkudov.jule.api

import com.google.gson.annotations.SerializedName

data class ApiResponse<E>(
    val status: ResponseStatus,
    val message: String?,
    val data: E,
)

package dashkudov.jule.model

import dashkudov.jule.api.ResponseStatus

data class ApiErrorModel(val message: String?, val status: ResponseStatus)

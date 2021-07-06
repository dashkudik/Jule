package dashkudov.jule.model

import dashkudov.jule.api.ResponseStatus
import dashkudov.jule.api.request.auth.AuthResponseStatus

data class ApiErrorModel(val message: String?, val status: ResponseStatus): InterpretedError {

    override val userFriendlyInterpretation: String
    get() = when (status) {
        ResponseStatus.ERROR_UNAUTHORIZED -> {
            when (message) {
                AuthResponseStatus.BAD_CREDENTIALS.name -> {
                    "Неправильный логин или пароль"
                }
                else -> "Неизвестная ошибка"
            }
        }
        else -> "Неизвестная ошибка"
    }
}

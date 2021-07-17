package dashkudov.jule.model

import dashkudov.jule.api.ResponseStatus
import dashkudov.jule.api.request.auth.AuthResponseMessage

data class ApiErrorModel(val message: String?, val status: ResponseStatus): InterpretedError {

    override val userFriendlyInterpretation: String
    get() = when (status) {
        ResponseStatus.ERROR_UNAUTHORIZED -> {
            when (message) {
                AuthResponseMessage.BAD_CREDENTIALS.name -> {
                    "Неправильный логин или пароль"
                }
                AuthResponseMessage.TOKEN_EXPIRED.name, AuthResponseMessage.TOKEN_INVALID.name -> {
                    String()
                }
                else -> "Неизвестная ошибка"
            }
        }
        else -> "Неизвестная ошибка"
    }
}

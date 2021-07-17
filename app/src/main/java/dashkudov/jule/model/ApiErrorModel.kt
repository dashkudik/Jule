package dashkudov.jule.model

import dashkudov.jule.api.ResponseStatus
import dashkudov.jule.api.request.auth.AuthResponseMessage
import dashkudov.jule.api.request.sign_up.SignUpResponseMessage

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
        ResponseStatus.ERROR_CONFLICT -> {
            when (message) {
                SignUpResponseMessage.LOGIN_RESERVED.name -> {
                    "Этот логин уже занят"
                }
                SignUpResponseMessage.EMAIL_RESERVED.name -> {
                    "Аккаунт с такой почтой уже существует"
                }
                else -> "Неизвестная ошибка"
            }
        }
        ResponseStatus.ERROR_BAD_REQUEST -> {
            when (message) {
                SignUpResponseMessage.INVALID_EMAIL_FORMAT.name -> {
                    "Введи почту правильно"
                }
                SignUpResponseMessage.INVALID_LOGIN_FORMAT.name -> {
                    "Логин может содержать латинские буквы, цифры, нижнее подчеркивание и точку"
                }
                else -> "Неизвестная ошибка"
            }
        }
        else -> "Неизвестная ошибка"
    }
}

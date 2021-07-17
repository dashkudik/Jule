package dashkudov.jule.model

data class LocalErrorModel(val localError: LocalError): InterpretedError {

    override val userFriendlyInterpretation: String
        get() = when (localError) {
            LocalError.IO -> "Что-то с интернетом"
            LocalError.NE_ZNAI -> "Неизвестная ошибка"
        }

}
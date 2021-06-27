package dashkudov.jule.presentation.start

sealed class StartAction {
    data class ImplicitAuth(val token: String): StartAction()
}

package dashkudov.jule.presentation.auth

import dashkudov.jule.mvi.News
import dashkudov.jule.presentation.start.StartNews


sealed class AuthNews: News {
    data class Message(val duration: Int, val content: String): AuthNews()
}
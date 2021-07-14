package dashkudov.jule.presentation.start

import dashkudov.jule.mvi.News


sealed class StartNews: News {
    data class Message(val duration: Int, val content: String): StartNews()
}
package dashkudov.jule.presentation.sign_up

import dashkudov.jule.mvi.News
import dashkudov.jule.presentation.auth.AuthNews

sealed class SignUpNews: News {
    data class Message(val duration: Int, val content: String): SignUpNews()
}
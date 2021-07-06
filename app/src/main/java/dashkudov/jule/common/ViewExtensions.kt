package dashkudov.jule.common

import android.view.View

object ViewExtensions {

    const val LOGO_FADE_IN_TIME: Long = 2000

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.gone() {
        visibility = View.GONE
    }
}
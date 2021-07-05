package dashkudov.jule.common

import android.view.View

object ViewExtensions {
    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.gone() {
        visibility = View.GONE
    }
}
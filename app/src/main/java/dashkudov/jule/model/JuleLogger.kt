package dashkudov.jule.model

import android.util.Log

class JuleLogger {
    private var className = UNKNOWN_CLASS

    fun connect(where: Class<*>) {
        Log.i("TEST1", "Connect to ${where.simpleName}")
        className = where.simpleName
    }

    fun log(what: String) {
        Log.d(TAG_JULE_LOGGER + className, what)
    }

    private companion object {
        const val UNKNOWN_CLASS = "UnknownClass"
        const val TAG_JULE_LOGGER = "JULE LOGGER   |   "
    }
}
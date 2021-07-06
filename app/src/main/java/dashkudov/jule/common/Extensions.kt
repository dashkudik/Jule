package dashkudov.jule.common

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import dashkudov.jule.mvi.Middleware

object Extensions {
    fun Fragment.navigate(where: NavDirections) = findNavController().navigate(where)

    fun Middleware<*>.cut() = javaClass.simpleName.substring(0..javaClass.simpleName.length - MIDDLEWARE_LENGTH)

    const val MIDDLEWARE_LENGTH = 11
}
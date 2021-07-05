package dashkudov.jule.common

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

object Extensions {
    fun Fragment.navigate(where: NavDirections) = findNavController().navigate(where)
}
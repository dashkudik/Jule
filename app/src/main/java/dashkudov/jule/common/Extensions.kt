package dashkudov.jule.common

import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import dashkudov.jule.mvi.Middleware
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

object Extensions {
    fun Fragment.navigate(where: NavDirections) = findNavController().navigate(where)

    fun View.click(f: (View) -> Unit) = setOnClickListener { f(this) }

    fun EditText.string() = text.toString()

    fun <T> Flow<T>.launchWhenStarted(lifecycleScope: LifecycleCoroutineScope) {
        lifecycleScope.launchWhenStarted {
            this@launchWhenStarted.collect()
        }
    }

    fun View.clickToBack() {
        setOnClickListener {
            findNavController().popBackStack()
        }
    }

    const val MIDDLEWARE_LENGTH = 11
}
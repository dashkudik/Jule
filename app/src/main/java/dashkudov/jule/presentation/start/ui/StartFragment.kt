package dashkudov.jule.presentation.start.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import dagger.android.support.AndroidSupportInjection
import dashkudov.jule.R
import dashkudov.jule.model.JuleLogger
import dashkudov.jule.presentation.BaseFragment
import dashkudov.jule.presentation.auth.ui.AuthViewModel
import dashkudov.jule.presentation.start.StartAction
import dashkudov.jule.presentation.start.StartState
import kotlinx.android.synthetic.main.f_start.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartFragment: BaseFragment(R.layout.f_start) {

    @Inject lateinit var logger: JuleLogger

    val startViewModel by lazy {
        viewModelFactory.create(AuthViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
        logger.connect(this.javaClass)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startViewModel.bind(flow {
            logger.log("ImplicitAuth")
            emit(StartAction.ImplicitAuth)
        }) {
            logger.log("Render ${it.javaClass.simpleName}")
            when (it) {
                is StartState.Shown -> {
                    img.animate().apply {
                        duration = 2000
                        alpha(1f)
                        scaleX(3f)
                        scaleY(3f)
                    }
                }
                is StartState.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is StartState.ToFeed -> {
                    img.animate().apply {
                        duration = 2000
                        alpha(0f)
                        scaleX(1f)
                        scaleY(1f)
                    }
                }
                is StartState.ToAuth -> {
                    MainScope().launch {
                        it.message?.let {
                            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        }
                        img.animate().apply {
                            duration = 2000
                            alpha(0f)
                            scaleX(1f)
                            scaleY(1f)
                        }
                        delay(2000)
                        findNavController().navigate(StartFragmentDirections.startAuth())
                    }
                }
            }
        }
    }

    companion object {
        const val TAG = "StartFragment"
        const val TAG_FLOW = "UiFlow"
    }
}
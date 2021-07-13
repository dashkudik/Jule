package dashkudov.jule.presentation.start.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import dashkudov.jule.R
import dashkudov.jule.common.Extensions.navigate
import dashkudov.jule.model.JuleLogger
import dashkudov.jule.mvi.MviView
import dashkudov.jule.presentation.BaseFragment
import dashkudov.jule.presentation.start.StartState
import kotlinx.android.synthetic.main.f_start.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartFragment: BaseFragment(R.layout.f_start), MviView<StartState> {

    @Inject
    lateinit var logger: JuleLogger

    private val startViewModel by lazy { viewModelFactory.create(StartViewModel::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            startViewModel.bind()
            startViewModel.stateFlow.collect(::render)
        }
    }

    override fun render(state: StartState) {
        logger.log("Render state ${state.javaClass.simpleName}")
        when (state) {
            is StartState.Error -> {
                Toast.makeText(requireActivity(), state.message, Toast.LENGTH_SHORT).show()
            }
            is StartState.ToFeed -> {
                MainScope().launch {
                    animateLogo()
                    delay(LOGO_FADE_IN_TIME)
                    navigate(StartFragmentDirections.startFeed())
                }
            }
            is StartState.ToAuth -> {
                MainScope().launch {
                    state.message?.let {
                        Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
                    }
                    animateLogo()
                    delay(LOGO_FADE_IN_TIME)
                    navigate(StartFragmentDirections.startAuth())
                }
            }
        }
    }

    private fun animateLogo() {
        img.animate().apply {
            duration = LOGO_FADE_IN_TIME
            alpha(ALPHA_OUT)
            scaleX(SCALE_OUT)
            scaleY(SCALE_OUT)
        }
    }

    private companion object {
        const val LOGO_FADE_IN_TIME: Long = 400
        const val ALPHA_OUT = 0f
        const val SCALE_OUT = 3.5f
    }
}
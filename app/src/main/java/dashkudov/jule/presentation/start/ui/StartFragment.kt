package dashkudov.jule.presentation.start.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import dashkudov.jule.R
import dashkudov.jule.common.Extensions.launchWhenStarted
import dashkudov.jule.common.Extensions.navigate
import dashkudov.jule.model.JuleLogger
import dashkudov.jule.mvi.MviView
import dashkudov.jule.presentation.BaseFragment
import dashkudov.jule.presentation.start.StartAction
import dashkudov.jule.presentation.start.StartNews
import dashkudov.jule.presentation.start.StartState
import kotlinx.android.synthetic.main.f_start.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartFragment: BaseFragment(R.layout.f_start), MviView<StartState, StartNews> {

    @Inject
    lateinit var logger: JuleLogger

    private val startViewModel by lazy {
        viewModelFactory.create(StartViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logger.connect(javaClass)

        with(startViewModel) {
            bind(lifecycleScope, this@StartFragment)

            lifecycleScope.launchWhenStarted {
                obtainAction(StartAction.ImplicitAuth)
            }
        }
    }

    override fun renderState(state: StartState) {
        when (state) {
            is StartState.Default -> {
                lifecycleScope.launch {
                    state.navDirections?.let {
                        animateLogo()
                        delay(LOGO_FADE_IN_TIME)
                        navigate(it)
                    }
                }
            }
        }
    }

    override fun renderNews(new: StartNews) {
        when (new) {
            is StartNews.Message -> {
                Toast.makeText(requireActivity(), new.content, Toast.LENGTH_SHORT).show()
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
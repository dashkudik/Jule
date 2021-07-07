package dashkudov.jule.presentation.start.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import dagger.android.support.AndroidSupportInjection
import dashkudov.jule.R
import dashkudov.jule.common.Extensions.navigate
import dashkudov.jule.model.JuleLogger
import dashkudov.jule.presentation.BaseFragment
import dashkudov.jule.presentation.start.StartAction
import dashkudov.jule.presentation.start.StartState
import kotlinx.android.synthetic.main.f_start.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartFragment: BaseFragment(R.layout.f_start) {

    @Inject lateinit var logger: JuleLogger

    private val startViewModel by lazy {
        viewModelFactory.create(StartViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
        logger.connect(javaClass)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launchWhenStarted {
            launch {
                startViewModel.on(flow {
                    emit(StartAction.LogoAnimationSuspenseRequired)
                })
            }
            launch {
                startViewModel.startStateFlow.collect(::render)
            }
        }
    }

    private fun render(state: StartState) {
        logger.log(state.javaClass.simpleName)
        when (state) {
            is StartState.LogoShown -> {
                img.animate().apply {
                    duration = LOGO_FADE_IN_TIME
                    alpha(ALPHA_IN)
                    scaleX(SCALE_IN)
                    scaleY(SCALE_IN)
                }
            }
            is StartState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is StartState.ToFeed -> {
                Toast.makeText(context, "Тенис", Toast.LENGTH_SHORT).show()
            }
            is StartState.ToAuth -> {
                MainScope().launch {
                    state.message?.let {
                        Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
                    }
                    img.animate().apply {
                        duration = LOGO_FADE_OUT_TIME
                        alpha(ALPHA_OUT)
                        scaleX(SCALE_OUT)
                        scaleY(SCALE_OUT)
                    }
                    delay(LOGO_FADE_OUT_TIME)
                    navigate(StartFragmentDirections.startAuth())
                }
            }
        }
    }

    private companion object {
        const val LOGO_FADE_OUT_TIME: Long = 700
        const val LOGO_FADE_IN_TIME: Long = 2000
        const val ALPHA_OUT = 0f
        const val ALPHA_IN = 1f
        const val SCALE_OUT = 1.5f
        const val SCALE_IN = 2f
    }
}
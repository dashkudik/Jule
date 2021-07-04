package dashkudov.jule.presentation.start.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import dagger.android.support.AndroidSupportInjection
import dashkudov.jule.R
import dashkudov.jule.presentation.BaseFragment
import dashkudov.jule.presentation.start.StartAction
import dashkudov.jule.presentation.start.StartState
import kotlinx.android.synthetic.main.f_intro.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class StartFragment: BaseFragment(R.layout.f_intro) {

    val startViewModel by lazy {
        viewModelFactory.create(StartViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startViewModel.bind(flow {
            emit(StartAction.ImplicitAuth)
        }) {
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
                    img.visibility = View.GONE
                    tv_success.visibility = View.VISIBLE
                }
                is StartState.ToAuth -> {
                    img.visibility = View.GONE
                    tv_auth.visibility = View.VISIBLE
                    it.message?.let {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
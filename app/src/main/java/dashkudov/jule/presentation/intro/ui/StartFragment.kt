package dashkudov.jule.presentation.intro.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import dagger.android.support.AndroidSupportInjection
import dashkudov.jule.R
import dashkudov.jule.presentation.BaseFragment
import dashkudov.jule.presentation.intro.StartAction
import dashkudov.jule.presentation.intro.StartState
import kotlinx.android.synthetic.main.f_intro.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

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
            delay(1000)
            emit(StartAction.ImplicitAuth)
        }) {
            when (it) {
                is StartState.Shown -> {
                    img.visibility = View.VISIBLE
                }
                is StartState.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
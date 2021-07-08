package dashkudov.jule.presentation.auth.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import dagger.android.support.AndroidSupportInjection
import dashkudov.jule.R
import dashkudov.jule.model.JuleLogger
import dashkudov.jule.presentation.BaseFragment
import javax.inject.Inject

class AuthFragment: BaseFragment(R.layout.f_auth) {


    val authViewModel by lazy {
        viewModelFactory.create(FeedViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        const val TAG = "StartFragment"
        const val TAG_FLOW = "UiFlow"
    }
}
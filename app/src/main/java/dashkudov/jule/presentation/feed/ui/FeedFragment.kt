package dashkudov.jule.presentation.feed.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import dagger.android.support.AndroidSupportInjection
import dashkudov.jule.R
import dashkudov.jule.model.JuleLogger
import dashkudov.jule.presentation.BaseFragment
import dashkudov.jule.presentation.auth.ui.FeedViewModel
import javax.inject.Inject

class FeedFragment: BaseFragment(R.layout.f_feed) {

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
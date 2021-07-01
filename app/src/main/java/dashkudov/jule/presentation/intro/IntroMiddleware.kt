package dashkudov.jule.presentation.intro

import android.util.Log
import dashkudov.jule.mvi.Middleware
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

class IntroMiddleware: Middleware<IntroAction> {
    override fun bind(actions: Flow<IntroAction>): Flow<IntroAction> = flow {
        actions.collect {
            Log.i("TEST", "I combine ${it.javaClass.canonicalName} with ${it.javaClass.canonicalName}")
        }
    }
}
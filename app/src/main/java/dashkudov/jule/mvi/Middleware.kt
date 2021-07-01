package dashkudov.jule.mvi

import kotlinx.coroutines.flow.Flow

interface Middleware<Action> {
    fun bind(actions: Flow<Action>): Flow<Action>
}
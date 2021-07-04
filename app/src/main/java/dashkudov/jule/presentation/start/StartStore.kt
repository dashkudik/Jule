package dashkudov.jule.presentation.start

import dashkudov.jule.mvi.Store
import dashkudov.jule.repository.ApiRepository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartStore @Inject constructor(apiRepository: ApiRepository) : Store<StartAction, StartState>(apiRepository) {

}
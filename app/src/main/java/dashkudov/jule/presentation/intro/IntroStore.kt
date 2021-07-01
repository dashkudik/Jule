package dashkudov.jule.presentation.intro

import dashkudov.jule.mvi.Action
import dashkudov.jule.mvi.Store
import dashkudov.jule.repository.ApiRepository
import javax.inject.Inject

class IntroStore @Inject constructor(apiRepository: ApiRepository) : Store<IntroAction, IntroState>(apiRepository)
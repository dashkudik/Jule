package dashkudov.jule.presentation.auth

import dashkudov.jule.presentation.Store
import dashkudov.jule.repository.ApiRepository
import javax.inject.Inject

class AuthStore @Inject constructor(val repository: ApiRepository): Store()
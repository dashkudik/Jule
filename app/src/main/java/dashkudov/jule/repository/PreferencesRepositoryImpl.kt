package dashkudov.jule.repository

import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val preferences: SharedPreferences
): PreferencesRepository {
}
package viach.apps.storage.repository

import kotlinx.coroutines.flow.Flow
import viach.apps.storage.model.AppTheme
import viach.apps.storage.model.TypedAppSetting

interface AppSettingsRepository {
    fun getAll(): Flow<List<TypedAppSetting>>
    suspend fun setTheme(appTheme: AppTheme)
}
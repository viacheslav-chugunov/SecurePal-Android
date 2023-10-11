package viach.apps.lock.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import viach.apps.storage.model.Lock
import viach.apps.storage.model.TypedAppSetting
import viach.apps.storage.repository.AppSettingsRepository

internal class DefaultLockRepository(
    private val appSettingsRepository: AppSettingsRepository
) : LockRepository {
    override suspend fun getLock(): Flow<Lock> =
        appSettingsRepository.getAll().map { settings ->
            val setting = settings.firstOrNull { it is TypedAppSetting.Lock } as? TypedAppSetting.Lock
            setting?.lock ?: Lock.DEFAULT
        }

    override suspend fun setLock(lock: Lock) {
        appSettingsRepository.setLock(lock)
    }
}
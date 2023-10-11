package viach.apps.storage.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import viach.apps.encryption.repository.EncryptionRepository
import viach.apps.storage.model.AppTheme
import viach.apps.storage.model.Lock
import viach.apps.storage.model.TypedAppSetting
import viach.apps.storage.room.dao.AppSettingDao
import viach.apps.storage.room.entity.AppSettingEntity
import kotlin.coroutines.CoroutineContext

internal class DefaultAppSettingsRepository(
    private val appSettingDao: AppSettingDao,
    private val encryptionRepository: EncryptionRepository,
    private val coroutineContext: CoroutineContext
) : AppSettingsRepository {

    companion object {
        private const val NAME_THEME = "theme"
        private const val NAME_LOCK_TYPE = "lock-type"
        private const val NAME_LOCK_SECRET = "lock-secret"
    }

    override fun getAll(): Flow<List<TypedAppSetting>> = appSettingDao.getAll().map { appSettings ->
        val typedSettings = mutableListOf<TypedAppSetting>()
        appSettings.findSettings(NAME_THEME) { (theme) ->
            typedSettings += TypedAppSetting.AppTheme(AppTheme.valueOf(theme))
        }
        appSettings.findSettings(NAME_LOCK_SECRET, NAME_LOCK_TYPE) { (secret, type) ->
            typedSettings += TypedAppSetting.Lock(Lock(secret, Lock.Type.valueOf(type)))
        }
        typedSettings
    }

    override suspend fun setTheme(appTheme: AppTheme) =
        setSetting(NAME_THEME, appTheme.name)

    override suspend fun setLock(type: Lock) {
        setSetting(NAME_LOCK_TYPE, type.secret)
        setSetting(NAME_LOCK_SECRET, type.type.name)
    }

    private inline fun List<AppSettingEntity>.findSettings(vararg keys: String, onFound: (List<String>) -> Unit) {
        val settings = filter { keys.contains(it.name) }.map { it.decrypt(encryptionRepository).value }
        if (settings.isNotEmpty()) onFound(settings)
    }

    private suspend fun setSetting(key: String, value: String) = withContext(coroutineContext) {
        appSettingDao.set(AppSettingEntity(key, value).encrypt(encryptionRepository))
    }
}
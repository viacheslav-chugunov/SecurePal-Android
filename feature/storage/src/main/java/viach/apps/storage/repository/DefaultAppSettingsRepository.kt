package viach.apps.storage.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import viach.apps.encryption.repository.EncryptionRepository
import viach.apps.storage.model.AppDeviceBlock
import viach.apps.storage.model.AppTheme
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
        private const val NAME_DEVICE_BLOCK_TYPE = "device-block-type"
        private const val NAME_DEVICE_BLOCK_VALUE = "device-block-value"
    }

    override fun getAll(): Flow<List<TypedAppSetting>> = appSettingDao.getAll().map { appSettings ->
        appSettings
            .filter { setting ->
                setting.name in setOf(NAME_THEME, NAME_DEVICE_BLOCK_TYPE, NAME_DEVICE_BLOCK_VALUE)
            }.map { setting ->
                val encryptedSetting = setting.encrypt(encryptionRepository)
                when (setting.name) {
                    NAME_THEME -> {
                        TypedAppSetting.AppTheme(AppTheme.valueOf(encryptedSetting.value))
                    }
                    else -> throw IllegalStateException()
                }
        }
    }

    override suspend fun setTheme(appTheme: AppTheme) = withContext(coroutineContext) {
        val setting = AppSettingEntity(NAME_THEME, appTheme.name).decrypt(encryptionRepository)
        appSettingDao.set(setting)
    }

    override suspend fun setDeviceBLock(appDeviceBlock: AppDeviceBlock) = withContext(coroutineContext) {
        val setting = AppSettingEntity(NAME_DEVICE_BLOCK_TYPE, appDeviceBlock.type).decrypt(encryptionRepository)
        appSettingDao.set(setting)
    }
}
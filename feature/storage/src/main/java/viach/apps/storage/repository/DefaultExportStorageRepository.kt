package viach.apps.storage.repository

import kotlinx.coroutines.withContext
import viach.apps.shared.repository.JsonRepository
import viach.apps.storage.room.dao.AppSettingDao
import viach.apps.storage.room.dao.AuthRecordDao
import viach.apps.storage.room.dao.CardRecordDao
import viach.apps.storage.room.dao.NoteRecordDao
import kotlin.coroutines.CoroutineContext

internal class DefaultExportStorageRepository(
    private val jsonRepository: JsonRepository,
    private val appSettingsRepository: AppSettingDao,
    private val authRecordRepository: AuthRecordDao,
    private val noteRecordRepository: NoteRecordDao,
    private val cardRecordRepository: CardRecordDao
    private val coroutineContext: CoroutineContext
) : ExportStorageRepository {

    override suspend fun toJson(): String = withContext(coroutineContext) {
        TODO("Not yet implemented")
    }

    override suspend fun fromJson(json: String) = withContext(coroutineContext) {
        TODO("Not yet implemented")
    }

}
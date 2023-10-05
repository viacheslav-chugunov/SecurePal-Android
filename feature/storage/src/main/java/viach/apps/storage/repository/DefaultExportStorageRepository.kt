package viach.apps.storage.repository

import kotlinx.coroutines.withContext
import viach.apps.shared.repository.JsonRepository
import viach.apps.storage.model.export.ExportAppSetting
import viach.apps.storage.model.export.ExportAuthRecord
import viach.apps.storage.model.export.ExportCardRecord
import viach.apps.storage.model.export.ExportNoteRecord
import viach.apps.storage.model.export.ExportStorageContent
import viach.apps.storage.room.dao.AppSettingDao
import viach.apps.storage.room.dao.AuthRecordDao
import viach.apps.storage.room.dao.CardRecordDao
import viach.apps.storage.room.dao.NoteRecordDao
import kotlin.coroutines.CoroutineContext

internal class DefaultExportStorageRepository(
    private val jsonRepository: JsonRepository,
    private val appSettingsDao: AppSettingDao,
    private val authRecordDao: AuthRecordDao,
    private val noteRecordDao: NoteRecordDao,
    private val cardRecordDao: CardRecordDao,
    private val coroutineContext: CoroutineContext
) : ExportStorageRepository {

    override suspend fun toJson(): String = withContext(coroutineContext) {
        val content = ExportStorageContent(
            appSettingsDao.getAllCurrent().map { ExportAppSetting(it) },
            authRecordDao.getAllCurrent().map { ExportAuthRecord(it) },
            noteRecordDao.getAllCurrent().map { ExportNoteRecord(it) },
            cardRecordDao.getAllCurrent().map { ExportCardRecord(it) }
        )
        jsonRepository.toJson(content)
    }

    override suspend fun fromJson(json: String) = withContext(coroutineContext) {
        val content = jsonRepository.fromJson(json, ExportStorageContent::class)
        if (content.appSettings != null) {
            appSettingsDao.setAll(content.appSettings.map { it.toEntity() })
        }
        if (content.authRecords != null) {
            authRecordDao.addAll(content.authRecords.map { it.toEntity() })
        }
        if (content.noteRecords != null) {
            noteRecordDao.addAll(content.noteRecords.map { it.toEntity() })
        }
        if (content.cardRecords != null) {
            cardRecordDao.addAll(content.cardRecords.map { it.toEntity() })
        }
    }

}
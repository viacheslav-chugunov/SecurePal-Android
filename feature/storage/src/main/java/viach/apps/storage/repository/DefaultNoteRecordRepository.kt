package viach.apps.storage.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import viach.apps.encryption.repository.EncryptionRepository
import viach.apps.storage.model.NoteRecord
import viach.apps.storage.room.dao.NoteRecordDao
import viach.apps.storage.room.entity.NoteRecordEntity
import kotlin.coroutines.CoroutineContext

internal class DefaultNoteRecordRepository(
    private val noteRecordDao: NoteRecordDao,
    private val encryptionRepository: EncryptionRepository,
    private val coroutineContext: CoroutineContext
) : NoteRecordRepository {
    
    override fun getAll(): Flow<List<NoteRecord>> =
        noteRecordDao.getAll().map { list -> list.map { it.decrypt(encryptionRepository) } }

    override suspend fun addRecord(record: NoteRecord) = withContext(coroutineContext) {
        noteRecordDao.add(NoteRecordEntity(record).encrypt(encryptionRepository))
    }

    override suspend fun removeRecord(record: NoteRecord) = withContext(coroutineContext) {
        noteRecordDao.remove(NoteRecordEntity(record).encrypt(encryptionRepository))
    }

    override suspend fun updateRecord(record: NoteRecord) = withContext(coroutineContext) {
        noteRecordDao.update(NoteRecordEntity(record).encrypt(encryptionRepository))
    }
}
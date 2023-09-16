package viach.apps.storage.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import viach.apps.encryption.repository.EncryptionRepository
import viach.apps.storage.model.CardRecord
import viach.apps.storage.room.dao.CardRecordDao
import viach.apps.storage.room.entity.CardRecordEntity
import kotlin.coroutines.CoroutineContext

internal class DefaultCardRecordRepository(
    private val cardRecordDao: CardRecordDao,
    private val encryptionRepository: EncryptionRepository,
    private val coroutineContext: CoroutineContext
) : CardRecordRepository {

    override fun getAll(): Flow<List<CardRecord>> =
        cardRecordDao.getAll().map { list -> list.map { it.decrypt(encryptionRepository) } }

    override suspend fun addRecord(record: CardRecord) = withContext(coroutineContext) {
        cardRecordDao.add(CardRecordEntity(record).encrypt(encryptionRepository))
    }

    override suspend fun removeRecord(record: CardRecord) = withContext(coroutineContext) {
        cardRecordDao.remove(CardRecordEntity(record).encrypt(encryptionRepository))
    }

    override suspend fun updateRecord(record: CardRecord) = withContext(coroutineContext) {
        cardRecordDao.update(CardRecordEntity(record).encrypt(encryptionRepository))
    }
}
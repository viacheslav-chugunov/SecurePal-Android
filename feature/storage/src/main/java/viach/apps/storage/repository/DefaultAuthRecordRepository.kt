package viach.apps.storage.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import viach.apps.encryption.repository.EncryptionRepository
import viach.apps.storage.model.AuthRecord
import viach.apps.storage.room.dao.AuthRecordDao
import viach.apps.storage.room.entity.AuthRecordEntity
import kotlin.coroutines.CoroutineContext

internal class DefaultAuthRecordRepository(
    private val authRecordDao: AuthRecordDao,
    private val encryptionRepository: EncryptionRepository,
    private val coroutineContext: CoroutineContext
) : AuthRecordRepository {

    override fun getAll(): Flow<List<AuthRecord>> =
        authRecordDao.getAll().map { list -> list.map { it.decrypt(encryptionRepository) } }

    override suspend fun addRecord(record: AuthRecord) = withContext(coroutineContext) {
        authRecordDao.add(AuthRecordEntity(record).encrypt(encryptionRepository))
    }

    override suspend fun removeRecord(record: AuthRecord) = withContext(coroutineContext) {
        authRecordDao.remove(AuthRecordEntity(record).encrypt(encryptionRepository))
    }

    override suspend fun updateRecord(record: AuthRecord) = withContext(coroutineContext) {
        authRecordDao.update(AuthRecordEntity(record).encrypt(encryptionRepository))
    }
}
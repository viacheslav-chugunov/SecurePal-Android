package viach.apps.storage.repository

import kotlinx.coroutines.flow.Flow
import viach.apps.storage.model.AuthRecord
import viach.apps.storage.model.CardRecord
import viach.apps.storage.model.NoteRecord
import viach.apps.storage.model.Record

interface AuthRecordRepository : TypedRecordRepository<AuthRecord>
interface CardRecordRepository : TypedRecordRepository<CardRecord>
interface NoteRecordRepository : TypedRecordRepository<NoteRecord>

interface TypedRecordRepository<Type : Record> {
    fun getAll(): Flow<List<Type>>
    fun getByCreatedAt(createdAt: Long): Flow<Type>
    suspend fun addRecord(record: Type)
    suspend fun removeRecord(record: Type)
    suspend fun updateRecord(record: Type)
}
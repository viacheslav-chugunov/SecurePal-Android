package viach.apps.lock.repository

import kotlinx.coroutines.flow.Flow
import viach.apps.storage.model.Lock

interface LockRepository {
    suspend fun getLock(): Flow<Lock>
    suspend fun setLock(lock: Lock)
}
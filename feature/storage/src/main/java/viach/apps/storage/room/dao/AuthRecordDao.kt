package viach.apps.storage.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import viach.apps.storage.room.entity.AuthRecordEntity
import viach.apps.storage.room.entity.CardRecordEntity

@Dao
internal interface AuthRecordDao {
    @Query("SELECT * FROM AUTH_RECORD ORDER BY CREATED_AT DESC")
    fun getAll(): Flow<List<AuthRecordEntity>>

    @Query("SELECT * FROM AUTH_RECORD WHERE CREATED_AT=:createdAt LIMIT 1")
    fun getByCreatedAt(createdAt: Long): Flow<AuthRecordEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(record: AuthRecordEntity)

    @Delete
    suspend fun remove(record: AuthRecordEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(record: AuthRecordEntity)
}
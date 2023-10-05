package viach.apps.storage.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import viach.apps.storage.room.entity.CardRecordEntity

@Dao
internal interface CardRecordDao {
    @Query("SELECT * FROM CARD_RECORD")
    suspend fun getAllCurrent(): List<CardRecordEntity>

    @Query("SELECT * FROM CARD_RECORD ORDER BY CREATED_AT DESC")
    fun getAll(): Flow<List<CardRecordEntity>>

    @Query("SELECT * FROM CARD_RECORD WHERE CREATED_AT=:createdAt LIMIT 1")
    fun getByCreatedAt(createdAt: Long): Flow<CardRecordEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(record: CardRecordEntity)

    @Delete
    suspend fun remove(record: CardRecordEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(record: CardRecordEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(records: List<CardRecordEntity>)
}
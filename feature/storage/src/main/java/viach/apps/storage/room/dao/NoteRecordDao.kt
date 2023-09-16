package viach.apps.storage.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import viach.apps.storage.room.entity.NoteRecordEntity

@Dao
internal interface NoteRecordDao {
    @Query("SELECT * FROM NOTE_RECORD ORDER BY CREATED_AT DESC")
    fun getAll(): Flow<List<NoteRecordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(record: NoteRecordEntity)

    @Delete
    suspend fun remove(record: NoteRecordEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(record: NoteRecordEntity)
}
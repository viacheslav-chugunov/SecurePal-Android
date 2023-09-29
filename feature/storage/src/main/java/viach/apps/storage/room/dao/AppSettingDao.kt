package viach.apps.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import viach.apps.storage.room.entity.AppSettingEntity

@Dao
internal interface AppSettingDao {
    @Query("SELECT * FROM APP_SETTING")
    fun getAll(): Flow<List<AppSettingEntity>>

    @Query("SELECT * FROM APP_SETTING ")
    fun get(name: String): Flow<AppSettingEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun set(appSettingEntity: AppSettingEntity)
}
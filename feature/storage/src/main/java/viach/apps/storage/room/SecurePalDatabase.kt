package viach.apps.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import viach.apps.storage.room.dao.AuthRecordDao
import viach.apps.storage.room.dao.CardRecordDao
import viach.apps.storage.room.dao.NoteRecordDao
import viach.apps.storage.room.entity.AuthRecordEntity
import viach.apps.storage.room.entity.CardRecordEntity
import viach.apps.storage.room.entity.NoteRecordEntity

@Database(version = 1, entities = [
    AuthRecordEntity::class,
    CardRecordEntity::class,
    NoteRecordEntity::class
])
internal abstract class SecurePalDatabase : RoomDatabase() {
    abstract val authRecordDao: AuthRecordDao
    abstract val noteRecordDao: NoteRecordDao
    abstract val cardRecordDao: CardRecordDao
}
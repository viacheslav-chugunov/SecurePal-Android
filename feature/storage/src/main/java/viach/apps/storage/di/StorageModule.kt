package viach.apps.storage.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import viach.apps.encryption.repository.EncryptionRepository
import viach.apps.shared.repository.JsonRepository
import viach.apps.storage.repository.AppSettingsRepository
import viach.apps.storage.repository.AuthRecordRepository
import viach.apps.storage.repository.CardRecordRepository
import viach.apps.storage.repository.DefaultAppSettingsRepository
import viach.apps.storage.repository.DefaultAuthRecordRepository
import viach.apps.storage.repository.DefaultCardRecordRepository
import viach.apps.storage.repository.DefaultExportStorageRepository
import viach.apps.storage.repository.DefaultNoteRecordRepository
import viach.apps.storage.repository.ExportStorageRepository
import viach.apps.storage.repository.NoteRecordRepository
import viach.apps.storage.room.SecurePalDatabase
import viach.apps.storage.room.dao.AppSettingDao
import viach.apps.storage.room.dao.AuthRecordDao
import viach.apps.storage.room.dao.CardRecordDao
import viach.apps.storage.room.dao.NoteRecordDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class StorageModule {

    @Provides
    fun providesAuthRecordRepository(
        authRecordDao: AuthRecordDao,
        encryptionRepository: EncryptionRepository
    ): AuthRecordRepository = DefaultAuthRecordRepository(
        authRecordDao,
        encryptionRepository,
        Dispatchers.IO
    )

    @Provides
    fun providesCardRecordRepository(
        cardRecordDao: CardRecordDao,
        encryptionRepository: EncryptionRepository
    ): CardRecordRepository = DefaultCardRecordRepository(
        cardRecordDao,
        encryptionRepository,
        Dispatchers.IO
    )

    @Provides
    fun providesNoteRecordRepository(
        noteRecordDao: NoteRecordDao,
        encryptionRepository: EncryptionRepository
    ): NoteRecordRepository = DefaultNoteRecordRepository(
        noteRecordDao,
        encryptionRepository,
        Dispatchers.IO
    )

    @Provides
    fun providesAppSettingRepository(
        appSettingDao: AppSettingDao,
        encryptionRepository: EncryptionRepository
    ): AppSettingsRepository = DefaultAppSettingsRepository(
        appSettingDao,
        encryptionRepository,
        Dispatchers.IO
    )

    @Provides
    fun providesExportStorageRepository(
        jsonRepository: JsonRepository,
        appSettingsRepository: AppSettingDao,
        authRecordRepository: AuthRecordDao,
        noteRecordRepository: NoteRecordDao,
        cardRecordRepository: CardRecordDao
    ): ExportStorageRepository = DefaultExportStorageRepository(
        jsonRepository,
        appSettingsRepository,
        authRecordRepository,
        noteRecordRepository,
        cardRecordRepository,
        Dispatchers.IO
    )

    @Provides
    @Singleton
    fun providesSecurePalDatabase(
        @ApplicationContext
        context: Context,
    ): SecurePalDatabase = Room.databaseBuilder(
        context,
        SecurePalDatabase::class.java,
        "SecurePal.db"
    ).build()

    @Provides
    fun providesAuthRecordDao(
        securePalDatabase: SecurePalDatabase
    ): AuthRecordDao = securePalDatabase.authRecordDao

    @Provides
    fun providesCardRecordDao(
        securePalDatabase: SecurePalDatabase
    ): CardRecordDao = securePalDatabase.cardRecordDao

    @Provides
    fun providesNoteRecordDao(
        securePalDatabase: SecurePalDatabase
    ): NoteRecordDao = securePalDatabase.noteRecordDao

    @Provides
    fun provideAppSettingDao(
        securePalDatabase: SecurePalDatabase
    ): AppSettingDao = securePalDatabase.appSettingDao
}
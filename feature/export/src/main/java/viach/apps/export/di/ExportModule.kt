package viach.apps.export.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import viach.apps.encryption.repository.EncryptionRepository
import viach.apps.export.repository.DefaultExportRepository
import viach.apps.export.repository.ExportRepository
import viach.apps.shared.repository.TimeRepository
import viach.apps.storage.repository.ExportStorageRepository

@Module
@InstallIn(SingletonComponent::class)
internal class ExportModule {

    @Provides
    fun providesExportRepository(
        @ApplicationContext
        context: Context,
        exportStorageRepository: ExportStorageRepository,
        encryptionRepository: EncryptionRepository,
        timeRepository: TimeRepository
    ): ExportRepository = DefaultExportRepository(
        context,
        exportStorageRepository,
        encryptionRepository,
        timeRepository,
        Dispatchers.IO
    )

}
package viach.apps.lock.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import viach.apps.lock.repository.DefaultLockRepository
import viach.apps.lock.repository.LockRepository
import viach.apps.storage.repository.AppSettingsRepository

@Module
@InstallIn(SingletonComponent::class)
internal class LockModule {

    @Provides
    fun providesLockRepository(
        appSettingsRepository: AppSettingsRepository
    ): LockRepository =
        DefaultLockRepository(appSettingsRepository)

}
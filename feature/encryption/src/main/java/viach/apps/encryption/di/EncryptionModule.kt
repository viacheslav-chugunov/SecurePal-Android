package viach.apps.encryption.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import viach.apps.encryption.repository.DefaultEncryptionRepository
import viach.apps.encryption.repository.EncryptionRepository
import viach.apps.shared.BuildConstants
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
internal class EncryptionModule {

    @Provides
    fun provideEncryptionRepository(
        @Named(BuildConstants.ENCRYPTION_TOKEN)
        encryptionToken: String
    ): EncryptionRepository = DefaultEncryptionRepository(encryptionToken)

}
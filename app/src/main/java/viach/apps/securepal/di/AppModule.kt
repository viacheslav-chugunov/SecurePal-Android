package viach.apps.securepal.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import viach.apps.securepal.BuildConfig
import viach.apps.shared.BuildConstants
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Named(BuildConstants.ENCRYPTION_TOKEN)
    fun provideEncryptionToken(): String = BuildConfig.ENCRYPTION_TOKEN

}
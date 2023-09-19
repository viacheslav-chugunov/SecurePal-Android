package viach.apps.shared.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import viach.apps.shared.repository.DefaultTimeRepository
import viach.apps.shared.repository.TimeRepository
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
class SharedModule {

    @Provides
    fun providesTimeRepository(): TimeRepository = DefaultTimeRepository(Locale.ENGLISH)

}
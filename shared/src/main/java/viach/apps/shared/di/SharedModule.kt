package viach.apps.shared.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import viach.apps.shared.repository.ClipboardRepository
import viach.apps.shared.repository.DefaultClipboardRepository
import viach.apps.shared.repository.DefaultTimeRepository
import viach.apps.shared.repository.TimeRepository
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
class SharedModule {

    @Provides
    fun providesTimeRepository(): TimeRepository = DefaultTimeRepository(Locale.ENGLISH)

    @Provides
    fun providesClipboardRepository(
        @ApplicationContext
        context: Context
    ): ClipboardRepository = DefaultClipboardRepository(context)
}
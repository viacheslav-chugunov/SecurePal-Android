package viach.apps.export.repository

import kotlinx.coroutines.withContext
import viach.apps.storage.repository.AppSettingsRepository
import kotlin.coroutines.CoroutineContext

class DefaultExportRepository(
    private val settingsRepository: AppSettingsRepository,
    private val coroutineContext: CoroutineContext
) : ExportRepository {

    override suspend fun export() = withContext(coroutineContext) {

    }
}
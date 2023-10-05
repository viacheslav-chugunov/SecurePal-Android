package viach.apps.export.repository

interface ExportRepository {
    suspend fun export(): Boolean
}
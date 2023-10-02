package viach.apps.storage.repository

interface ExportStorageRepository {
    suspend fun toJson(): String
    suspend fun fromJson(json: String)
}
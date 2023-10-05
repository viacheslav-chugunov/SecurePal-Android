package viach.apps.export.repository

import android.net.Uri

interface ExportRepository {
    suspend fun export(): Boolean
    suspend fun import(uri: Uri): Boolean
}
package viach.apps.export.repository

import android.os.Environment
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.coroutines.withContext
import viach.apps.encryption.repository.EncryptionRepository
import viach.apps.shared.repository.TimeRepository
import viach.apps.storage.repository.ExportStorageRepository
import java.io.File
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

internal class DefaultExportRepository(
    private val exportStorageRepository: ExportStorageRepository,
    private val encryptionRepository: EncryptionRepository,
    private val timeRepository: TimeRepository,
    private val coroutineContext: CoroutineContext
) : ExportRepository {

    override suspend fun export(): Boolean = withContext(coroutineContext) {
        val appDataJson = exportStorageRepository.toJson()
        val encryptedAppDataJson = encryptionRepository.encrypt(appDataJson)
        val downloadsFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val exportDate = timeRepository.createCurrentDate("dd.MM.yyyy-HH.mm.ss")
        try {
            val exportFile = File(downloadsFile, "secure-pal-$exportDate.export")
            exportFile.appendText(encryptedAppDataJson)
            exportFile.createNewFile()
            Log.d("MyTest", "folder created")
            true
        } catch (e: Exception) {
            Log.e("MyTest", e.stackTraceToString())
            false
        }
    }
}
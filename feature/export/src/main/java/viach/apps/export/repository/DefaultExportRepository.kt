package viach.apps.export.repository

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import kotlinx.coroutines.withContext
import viach.apps.encryption.repository.EncryptionRepository
import viach.apps.shared.repository.TimeRepository
import viach.apps.storage.repository.ExportStorageRepository
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import kotlin.coroutines.CoroutineContext


internal class DefaultExportRepository(
    private val context: Context,
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
            val exportFile = File(downloadsFile, "secure-pal-export-$exportDate.txt")
            exportFile.appendText(encryptedAppDataJson)
            exportFile.createNewFile()
            Log.d("MyTest", "folder created")
            true
        } catch (e: Exception) {
            Log.e("MyTest", e.stackTraceToString())
            false
        }
    }

    override suspend fun import(uri: Uri): Boolean = withContext(coroutineContext) {
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val contentStringBuilder = StringBuilder()
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                contentStringBuilder.append(line)
            }
            inputStream?.close()
            val contentJson = contentStringBuilder.toString()
            val decryptedContentJson = encryptionRepository.decrypt(contentJson)
            exportStorageRepository.fromJson(decryptedContentJson)
            true
        } catch (e: Exception) {
            false
        }
    }
}
package viach.apps.shared.repository

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

internal class DefaultClipboardRepository(
    private val context: Context
) : ClipboardRepository {
    override fun copy(text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("SecurePal", text)
        clipboard.setPrimaryClip(clip)
    }
}
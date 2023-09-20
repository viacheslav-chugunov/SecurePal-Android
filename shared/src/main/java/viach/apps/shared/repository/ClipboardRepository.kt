package viach.apps.shared.repository

interface ClipboardRepository {
    fun copy(text: String)
}
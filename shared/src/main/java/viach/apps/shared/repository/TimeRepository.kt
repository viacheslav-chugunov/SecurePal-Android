package viach.apps.shared.repository

interface TimeRepository {
    fun createDate(timestamp: Long, pattern: String): String
}
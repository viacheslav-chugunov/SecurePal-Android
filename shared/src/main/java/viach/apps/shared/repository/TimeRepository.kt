package viach.apps.shared.repository

interface TimeRepository {
    fun createDate(timestamp: Long, pattern: String = "dd MMMM yyyy, HH:mm"): String
    fun createCurrentDate(pattern: String = "dd MMMM yyyy, HH:mm"): String
}
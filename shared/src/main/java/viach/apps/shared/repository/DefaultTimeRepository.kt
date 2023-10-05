package viach.apps.shared.repository

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal class DefaultTimeRepository(
    private val locale: Locale
) : TimeRepository {

    override fun createDate(timestamp: Long, pattern: String): String {
        val formatter = SimpleDateFormat(pattern, locale)
        return formatter.format(Date(timestamp))
    }

    override fun createCurrentDate(pattern: String): String {
        val formatter = SimpleDateFormat(pattern, locale)
        return formatter.format(Date(System.currentTimeMillis()))
    }

}
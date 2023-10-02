package viach.apps.storage.model.export

import viach.apps.storage.model.AuthRecord
import viach.apps.storage.room.entity.AuthRecordEntity

internal class ExportAuthRecord(
    private val auth: String?,
    private val password: String?,
    private val title: String?,
    private val note: String?,
    private val createdAt: Long?,
    private val updatedAt: Long?
) {

    fun toEntity(): AuthRecordEntity = AuthRecordEntity(
        auth ?: "",
        password ?: "",
        title ?: "",
        note ?: "",
        createdAt ?: 0L,
        updatedAt ?: 0L
    )

}
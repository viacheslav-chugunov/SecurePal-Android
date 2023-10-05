package viach.apps.storage.model.export

import viach.apps.storage.model.NoteRecord
import viach.apps.storage.room.entity.NoteRecordEntity

internal class ExportNoteRecord(
    private val title: String?,
    private val note: String?,
    private val createdAt: Long?,
    private val updatedAt: Long?
) {

    constructor(noteRecord: NoteRecord) : this(
        noteRecord.title,
        noteRecord.note,
        noteRecord.createdAt,
        noteRecord.updatedAt
    )

    fun toEntity(): NoteRecordEntity = NoteRecordEntity(
        title ?: "",
        note ?: "",
        createdAt ?: 0,
        updatedAt ?: 0
    )

}
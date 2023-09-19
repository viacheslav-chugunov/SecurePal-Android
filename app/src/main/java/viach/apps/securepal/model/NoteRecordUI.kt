package viach.apps.securepal.model

import viach.apps.storage.model.NoteRecord

data class NoteRecordUI(
    override val title: String = "",
    override val note: String = "",
    override val createdAt: Long = System.currentTimeMillis(),
    override val updatedAt: Long = createdAt
) : NoteRecord {

    constructor(noteRecord: NoteRecord) : this(
        noteRecord.title,
        noteRecord.note,
        noteRecord.createdAt,
        noteRecord.updatedAt
    )

}
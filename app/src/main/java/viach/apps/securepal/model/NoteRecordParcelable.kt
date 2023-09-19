package viach.apps.securepal.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import viach.apps.storage.model.NoteRecord

@Parcelize
class NoteRecordParcelable(
    override val title: String,
    override val note: String,
    override val createdAt: Long,
    override val updatedAt: Long
) : NoteRecord, Parcelable {

    constructor(noteRecord: NoteRecord) : this(
        noteRecord.title,
        noteRecord.note,
        noteRecord.createdAt,
        noteRecord.updatedAt
    )

}
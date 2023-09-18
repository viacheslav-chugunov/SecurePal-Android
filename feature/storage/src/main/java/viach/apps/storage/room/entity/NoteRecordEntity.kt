package viach.apps.storage.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import viach.apps.encryption.repository.EncryptionRepository
import viach.apps.storage.model.NoteRecord

@Entity(tableName = "NOTE_RECORD", primaryKeys = ["CREATED_AT"])
internal class NoteRecordEntity(
    @ColumnInfo(name = "TITLE")
    override var title: String,
    @ColumnInfo(name = "NOTE")
    override var note: String,
    @ColumnInfo(name = "CREATED_AT")
    override var createdAt: Long,
    @ColumnInfo(name = "UPDATED_AT")
    override var updatedAt: Long
) : NoteRecord {

    constructor(noteRecord: NoteRecord) : this(
        noteRecord.title,
        noteRecord.note,
        noteRecord.createdAt,
        noteRecord.updatedAt
    )

    fun encrypt(encryptionRepository: EncryptionRepository): NoteRecordEntity = NoteRecordEntity(
        encryptionRepository.encrypt(title),
        encryptionRepository.encrypt(note),
        createdAt,
        updatedAt
    )

    fun decrypt(encryptionRepository: EncryptionRepository): NoteRecordEntity = NoteRecordEntity(
        encryptionRepository.decrypt(title),
        encryptionRepository.decrypt(note),
        createdAt,
        updatedAt
    )

}
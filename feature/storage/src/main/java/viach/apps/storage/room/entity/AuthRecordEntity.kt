package viach.apps.storage.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import viach.apps.encryption.repository.EncryptionRepository
import viach.apps.storage.model.AuthRecord

@Entity(tableName = "AUTH_RECORD", primaryKeys = ["CREATED_AT"])
internal class AuthRecordEntity(
    @ColumnInfo(name = "TITLE")
    override val title: String,
    @ColumnInfo(name = "AUTH")
    override val auth: String,
    @ColumnInfo(name = "PASSWORD")
    override val password: String,
    @ColumnInfo(name = "NOTE")
    override val note: String,
    @ColumnInfo(name = "CREATED_AT")
    override val createdAt: Long,
    @ColumnInfo(name = "UPDATED_AT")
    override val updatedAt: Long
): AuthRecord {

    constructor(authRecord: AuthRecord) : this(
        authRecord.title,
        authRecord.auth,
        authRecord.password,
        authRecord.note,
        authRecord.createdAt,
        authRecord.updatedAt
    )

    fun encrypt(encryptionRepository: EncryptionRepository): AuthRecordEntity = AuthRecordEntity(
        encryptionRepository.encrypt(title),
        encryptionRepository.encrypt(auth),
        encryptionRepository.encrypt(password),
        encryptionRepository.encrypt(note),
        createdAt,
        updatedAt
    )

    fun decrypt(encryptionRepository: EncryptionRepository): AuthRecordEntity = AuthRecordEntity(
        encryptionRepository.decrypt(title),
        encryptionRepository.decrypt(auth),
        encryptionRepository.decrypt(password),
        encryptionRepository.decrypt(note),
        createdAt,
        updatedAt
    )

}
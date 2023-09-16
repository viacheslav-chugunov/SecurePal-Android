package viach.apps.storage.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import viach.apps.encryption.repository.EncryptionRepository
import viach.apps.storage.model.CardRecord

@Entity(tableName = "CARD_RECORD", primaryKeys = ["CREATED_AT"])
internal class CardRecordEntity(
    @ColumnInfo(name = "TITLE")
    override val title: String,
    @ColumnInfo(name = "NOTE")
    override val note: String,
    @ColumnInfo(name = "OWNER")
    override val owner: String,
    @ColumnInfo(name = "NUMBER")
    override val number: String,
    @ColumnInfo(name = "CHECK")
    override val check: String,
    @ColumnInfo(name = "PIN")
    override val pin: String,
    @ColumnInfo(name = "EXPIRATION")
    override val expiration: String,
    @ColumnInfo(name = "CREATED_AT")
    override val createdAt: Long,
    @ColumnInfo(name = "UPDATED_AT")
    override val updatedAt: Long
) : CardRecord {

    constructor(cardRecord: CardRecord) : this(
        cardRecord.title,
        cardRecord.note,
        cardRecord.owner,
        cardRecord.number,
        cardRecord.check,
        cardRecord.pin,
        cardRecord.expiration,
        cardRecord.createdAt,
        cardRecord.updatedAt
    )

    fun encrypt(encryptionRepository: EncryptionRepository): CardRecordEntity = CardRecordEntity(
        encryptionRepository.encrypt(title),
        encryptionRepository.encrypt(note),
        encryptionRepository.encrypt(owner),
        encryptionRepository.encrypt(number),
        encryptionRepository.encrypt(check),
        encryptionRepository.encrypt(pin),
        encryptionRepository.encrypt(expiration),
        createdAt,
        updatedAt
    )

    fun decrypt(encryptionRepository: EncryptionRepository): CardRecordEntity = CardRecordEntity(
        encryptionRepository.decrypt(title),
        encryptionRepository.decrypt(note),
        encryptionRepository.decrypt(owner),
        encryptionRepository.decrypt(number),
        encryptionRepository.decrypt(check),
        encryptionRepository.decrypt(pin),
        expiration,
        createdAt,
        updatedAt
    )

}
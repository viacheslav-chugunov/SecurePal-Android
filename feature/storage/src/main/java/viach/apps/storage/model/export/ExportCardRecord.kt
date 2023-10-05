package viach.apps.storage.model.export

import viach.apps.storage.model.CardRecord
import viach.apps.storage.room.entity.CardRecordEntity

internal class ExportCardRecord(
    private val owner: String?,
    private val number: String?,
    private val check: String?,
    private val pin: String?,
    private val expiration: String?,
    private val title: String?,
    private val note: String?,
    private val createdAt: Long?,
    private val updatedAt: Long?
) {

    constructor(cardRecord: CardRecord) : this(
        cardRecord.owner,
        cardRecord.number,
        cardRecord.check,
        cardRecord.pin,
        cardRecord.expiration,
        cardRecord.title,
        cardRecord.note,
        cardRecord.createdAt,
        cardRecord.updatedAt
    )

    fun toEntity() : CardRecordEntity = CardRecordEntity(
        owner ?: "",
        number ?: "",
        check ?: "",
        pin ?: "",
        expiration ?: "",
        title ?: "",
        note ?: "",
        createdAt ?: 0L,
        updatedAt ?: 0L
    )

}
package viach.apps.securepal.model

import viach.apps.storage.model.CardRecord

data class CardRecordUI(
    override val owner: String = "",
    override val number: String = "",
    override val check: String = "",
    override val pin: String = "",
    override val expiration: String = "",
    override val title: String = "",
    override val note: String = "",
    override val createdAt: Long = System.currentTimeMillis(),
    override val updatedAt: Long = createdAt
) : CardRecord {

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

}
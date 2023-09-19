package viach.apps.securepal.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import viach.apps.storage.model.CardRecord

@Parcelize
class CardRecordParcelable(
    override val owner: String,
    override val number: String,
    override val check: String,
    override val pin: String,
    override val expiration: String,
    override val title: String,
    override val note: String,
    override val createdAt: Long,
    override val updatedAt: Long
) : CardRecord, Parcelable {

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
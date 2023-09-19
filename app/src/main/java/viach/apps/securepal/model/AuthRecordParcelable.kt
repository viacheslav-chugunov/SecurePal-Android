package viach.apps.securepal.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import viach.apps.storage.model.AuthRecord

@Parcelize
class AuthRecordParcelable(
    override val auth: String,
    override val password: String,
    override val title: String,
    override val note: String,
    override val createdAt: Long,
    override val updatedAt: Long
) : AuthRecord, Parcelable {

    constructor(authRecord: AuthRecord) : this(
        authRecord.auth,
        authRecord.password,
        authRecord.title,
        authRecord.note,
        authRecord.createdAt,
        authRecord.updatedAt
    )

}
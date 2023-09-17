package viach.apps.securepal.model

import viach.apps.storage.model.AuthRecord

data class AuthRecordUI(
    override val auth: String = "",
    override val password: String = "",
    override val title: String = "",
    override val note: String = "",
    override val createdAt: Long = System.currentTimeMillis(),
    override val updatedAt: Long = createdAt
) : AuthRecord
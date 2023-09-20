package viach.apps.securepal.ui.screen.showauthrecord

import viach.apps.securepal.Screen
import viach.apps.securepal.model.AuthRecordUI
import viach.apps.storage.model.AuthRecord

data class ShowAuthRecordState(
    val authRecord: AuthRecord = AuthRecordUI(),
    val openScreen: Screen? = null,
    val closeScreen: Boolean = false,
    val showPassword: Boolean = false,
    val createdDate: String = "",
    val updatedDate: String = "",
    val showMessage: String = ""
)
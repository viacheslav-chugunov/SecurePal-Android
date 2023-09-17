package viach.apps.securepal.ui.screen.authrecord

import viach.apps.securepal.model.AuthRecordUI

sealed interface AuthRecordAction {
    object SaveAuthRecord : AuthRecordAction
    class UpdateAuthRecord(val authRecord: AuthRecordUI) : AuthRecordAction
    class ShowPassword(val show: Boolean) : AuthRecordAction
}
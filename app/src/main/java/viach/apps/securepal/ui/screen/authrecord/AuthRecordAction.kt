package viach.apps.securepal.ui.screen.authrecord

import viach.apps.storage.model.AuthRecord

sealed interface AuthRecordAction {
    class SaveAuthRecord(val authRecord: AuthRecord): AuthRecordAction
}
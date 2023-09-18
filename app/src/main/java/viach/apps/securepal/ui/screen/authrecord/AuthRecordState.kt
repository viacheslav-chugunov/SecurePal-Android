package viach.apps.securepal.ui.screen.authrecord

import viach.apps.securepal.model.AuthRecordUI

data class AuthRecordState(
    val authRecord: AuthRecordUI = AuthRecordUI(),
    val showPassword: Boolean = false,
    val closeScreen: Boolean = false,
    val titleError: Boolean = false,
    val authError: Boolean = false
)
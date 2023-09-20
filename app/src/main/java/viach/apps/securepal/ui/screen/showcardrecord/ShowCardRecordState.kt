package viach.apps.securepal.ui.screen.showcardrecord

import viach.apps.securepal.Screen
import viach.apps.securepal.model.CardRecordUI
import viach.apps.storage.model.CardRecord

data class ShowCardRecordState(
    val cardRecord: CardRecord = CardRecordUI(),
    val openScreen: Screen? = null,
    val closeScreen: Boolean = false,
    val showCheck: Boolean = false,
    val showPin: Boolean = false,
    val createdDate: String = "",
    val updatedDate: String = "",
    val showMessageRes: Int = 0
)
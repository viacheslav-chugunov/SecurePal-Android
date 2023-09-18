package viach.apps.securepal.ui.screen.cardrecord

import viach.apps.securepal.model.CardRecordUI

data class CardRecordState(
    val cardRecord: CardRecordUI = CardRecordUI(),
    val closeScreen: Boolean = false,
    val showCheck: Boolean = false,
    val showPin: Boolean = false
)
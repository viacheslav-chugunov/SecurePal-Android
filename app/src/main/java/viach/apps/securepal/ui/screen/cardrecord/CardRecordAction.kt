package viach.apps.securepal.ui.screen.cardrecord

import viach.apps.securepal.model.CardRecordUI

sealed interface CardRecordAction {
    object SaveCardRecord : CardRecordAction
    class UpdateCardRecord(val cardRecord: CardRecordUI) : CardRecordAction
    class ShowPin(val show: Boolean) : CardRecordAction
    class ShowCheck(val show: Boolean) : CardRecordAction
}
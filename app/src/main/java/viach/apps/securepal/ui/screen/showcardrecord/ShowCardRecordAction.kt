package viach.apps.securepal.ui.screen.showcardrecord

import androidx.annotation.StringRes
import viach.apps.securepal.Screen

sealed interface ShowCardRecordAction {
    object DeleteCardRecord : ShowCardRecordAction
    class OpenScreen(val screen: Screen) : ShowCardRecordAction
    object HandleOpenedScreen : ShowCardRecordAction
    class ShowCheck(val show: Boolean) : ShowCardRecordAction
    class ShowPin(val show: Boolean) : ShowCardRecordAction
    class CopyToClipboard(val text: String, @StringRes val messageRes: Int) : ShowCardRecordAction
    object HandleShownMessage : ShowCardRecordAction
}
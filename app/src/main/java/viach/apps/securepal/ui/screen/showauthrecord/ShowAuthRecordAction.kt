package viach.apps.securepal.ui.screen.showauthrecord

import androidx.annotation.StringRes
import viach.apps.securepal.Screen

sealed interface ShowAuthRecordAction {
    object DeleteAuthRecord : ShowAuthRecordAction
    class OpenScreen(val screen: Screen) : ShowAuthRecordAction
    object HandleOpenedScreen : ShowAuthRecordAction
    class ShowPassword(val show: Boolean) : ShowAuthRecordAction
    class CopyToClipboard(val text: String, @StringRes val messageRes: Int) : ShowAuthRecordAction
    object HandleShownMessage : ShowAuthRecordAction
}
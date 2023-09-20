package viach.apps.securepal.ui.screen.showauthrecord

import viach.apps.securepal.Screen

sealed interface ShowAuthRecordAction {
    object DeleteAuthRecord : ShowAuthRecordAction
    class OpenScreen(val screen: Screen) : ShowAuthRecordAction
    object HandleOpenedScreen : ShowAuthRecordAction
    class ShowPassword(val show: Boolean) : ShowAuthRecordAction
    class CopyToClipboard(val text: String) : ShowAuthRecordAction
    object HandleShownMessage : ShowAuthRecordAction
}
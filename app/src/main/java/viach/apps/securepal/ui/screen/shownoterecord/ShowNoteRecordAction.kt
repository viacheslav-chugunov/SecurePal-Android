package viach.apps.securepal.ui.screen.shownoterecord

import androidx.annotation.StringRes
import viach.apps.securepal.Screen

sealed interface ShowNoteRecordAction {
    object DeleteNoteRecord : ShowNoteRecordAction
    class OpenScreen(val screen: Screen) : ShowNoteRecordAction
    object HandleOpenedScreen : ShowNoteRecordAction
    class CopyToClipboard(val text: String, @StringRes val messageRes: Int) : ShowNoteRecordAction
    object HandleShownMessage : ShowNoteRecordAction
}
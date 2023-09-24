package viach.apps.securepal.extension

import android.os.Parcelable
import androidx.navigation.NavController
import viach.apps.securepal.Screen
import viach.apps.securepal.model.AuthRecordParcelable
import viach.apps.securepal.model.CardRecordParcelable
import viach.apps.securepal.model.NoteRecordParcelable

fun NavController.navigate(screen: Screen) {
    val currentRoute = currentDestination?.route ?: ""
    if (screen.newTask) {
        popBackStack(currentRoute, inclusive = true)
        popBackStack()
    }

    navigate(screen.route)

    when (screen) {
        is Screen.EditAuthRecord -> putParcelable(Screen.EditAuthRecord.Key.AUTH_RECORD, AuthRecordParcelable(screen.authRecord))
        is Screen.EditCardRecord -> putParcelable(Screen.EditCardRecord.Key.CARD_RECORD, CardRecordParcelable(screen.cardRecord))
        is Screen.EditNoteRecord -> putParcelable(Screen.EditNoteRecord.Key.NOTE_RECORD, NoteRecordParcelable(screen.noteRecord))
        is Screen.ShowAuthRecord -> putParcelable(Screen.ShowAuthRecord.Key.AUTH_RECORD, AuthRecordParcelable(screen.authRecord))
        is Screen.ShowCardRecord -> putParcelable(Screen.ShowCardRecord.Key.CARD_RECORD, CardRecordParcelable(screen.cardRecord))
        is Screen.ShowNoteRecord -> putParcelable(Screen.ShowNoteRecord.Key.NOTE_RECORD, NoteRecordParcelable(screen.noteRecord))
        else -> {}
    }
}

private fun NavController.putParcelable(key: String, value: Parcelable) {
    currentBackStackEntry?.savedStateHandle?.set(key, value)
}

inline fun <reified R : Parcelable> NavController.getParcelable(key: String): R? {
    return currentBackStackEntry?.savedStateHandle?.get(key)
}

fun NavController.move(screen: Screen) {
    val currentRoute = currentDestination?.route ?: ""
    if (currentRoute == screen.route) return
    val isNavigated = popBackStack(currentRoute, inclusive = true)
    if (!isNavigated) navigate(screen)
}
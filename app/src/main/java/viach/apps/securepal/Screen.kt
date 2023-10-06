package viach.apps.securepal

import viach.apps.storage.model.AuthRecord
import viach.apps.storage.model.CardRecord
import viach.apps.storage.model.NoteRecord

sealed class Screen(val route: String, val newTask: Boolean = false) {
    object Lock : Screen(Route.LOCK)
    object Dashboard : Screen(Route.DASHBOARD, true)
    object Settings : Screen(Route.SETTINGS)
    object NewAuthRecord : Screen(Route.AUTH_RECORD)
    object NewNoteRecord : Screen(Route.NOTE_RECORD)
    object NewCardRecord : Screen(Route.CARD_RECORD)
    class EditAuthRecord(val authRecord: AuthRecord) : Screen(Route.AUTH_RECORD) {
        object Key {
            const val AUTH_RECORD = "auth-record"
        }
    }
    class EditNoteRecord(val noteRecord: NoteRecord) : Screen(Route.NOTE_RECORD) {
        object Key {
            const val NOTE_RECORD = "note-record"
        }
    }
    class EditCardRecord(val cardRecord: CardRecord) : Screen(Route.CARD_RECORD) {
        object Key {
            const val CARD_RECORD = "card-record"
        }
    }
    class ShowAuthRecord(val authRecord: AuthRecord) : Screen(Route.SHOW_AUTH_RECORD) {
        object Key {
            const val AUTH_RECORD = "auth-record"
        }
    }
    class ShowNoteRecord(val noteRecord: NoteRecord) : Screen(Route.SHOW_NOTE_RECORD) {
        object Key {
            const val NOTE_RECORD = "note-record"
        }
    }
    class ShowCardRecord(val cardRecord: CardRecord) : Screen(Route.SHOW_CARD_RECORD) {
        object Key {
            const val CARD_RECORD = "card-record"
        }
    }


    object Route {
        const val LOCK = "lock"
        const val DASHBOARD = "dashboard"
        const val SETTINGS = "settings"
        const val AUTH_RECORD = "auth-record"
        const val NOTE_RECORD = "note-record"
        const val CARD_RECORD = "card-record"
        const val SHOW_AUTH_RECORD = "show-auth-record"
        const val SHOW_NOTE_RECORD = "show-note-record"
        const val SHOW_CARD_RECORD = "show-card-record"
    }
}